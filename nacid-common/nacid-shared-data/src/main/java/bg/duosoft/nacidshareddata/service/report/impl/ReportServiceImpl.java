package bg.duosoft.nacidshareddata.service.report.impl;

import bg.duosoft.nacidshareddata.exception.ReportException;
import bg.duosoft.nacidshareddata.service.report.ReportService;
import freemarker.template.Configuration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.api.freemarker.java8.Java8ObjectWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.xhtmlrenderer.util.XRLog;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.01.2023
 * Time: 13:59
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    public static final String[] FONT_EXTENSIONS = new String[]{".ttf"};
    public static final String FONT_DIR = "/common/fonts";
    private static final String COMMON_MESSAGES_MESSAGES_DIR = "/common/messages/";

    @Value("${reports.base.directory:#{null}}")
    private String reportsBaseDirectory;

    private ConcurrentHashMap<String, Properties> messages;
    private String resourceFolder;
    private FreemarkerGenerator freemarkerGenerator;

    @PostConstruct
    public void init() {
        if (Objects.isNull(reportsBaseDirectory)) {
            log.warn("ReportService is not configured, because reportsBaseDirectory is empty !");
            return;
        }

        XRLog.setLoggingEnabled(true);
        try {
            File freemarkerDir = ResourceUtils.getFile(reportsBaseDirectory);
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
            configuration.setTemplateExceptionHandler(new CustomTemplateExceptionHandler());
            configuration.setDirectoryForTemplateLoading(freemarkerDir);
            configuration.setObjectWrapper(new Java8ObjectWrapper(configuration.getIncompatibleImprovements()));

            this.messages = new ConcurrentHashMap<>();
            this.resourceFolder = freemarkerDir.getAbsolutePath();
            this.freemarkerGenerator = new FreemarkerGenerator(configuration);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("init(): SEVERE: Could not resolve the resource folder for iText Report generation!");
            throw new RuntimeException("init(): SEVERE: Could not resolve the resource folder for iText Report generation!", e);
        }
    }

    @Override
    public byte[] generateReport(String template, String localeCode, Object... args) {
        Boolean isDraft = false;
        if (args.length > 1 && args[1] != null) {
            if (args[1] instanceof String) {
                isDraft = Boolean.parseBoolean((String) args[1]);
            } else if (args[1] instanceof Boolean) {
                isDraft = (Boolean) args[1];
            }
        }
        if (template == null) {
            throw new ReportException("Empty template file property setting");
        }
        return createPdf(isDraft, template, localeCode, args);
    }

    private byte[] createPdf(Boolean isDraft, String template, String localeCode, Object... args) {
        try {
            String generatedHTML = generateHTML(template, localeCode, isDraft, args);

            log.debug("Generated HTML for template [" + template + "], locale [" + localeCode + "] and " + args.length + " arguments: " + generatedHTML);

            return ITextPdfBuilder.createPdf(generatedHTML,
                    new File(new File(resourceFolder + File.separator + template).getParent()).toURI().toString(),
                    new PdfCreationListener(new WatermarkPageEvent(resourceFolder, isDraft, localeCode)),
                    ITextPdfBuilder.getFontPaths(resourceFolder + FONT_DIR, FONT_EXTENSIONS));

        } catch (Exception e) {
            log.error("generateReport(): Error during generating report: \n" + e.getMessage(), e);
            throw new ReportException("generateReport(): Error during generating report", e);
        }
    }

    private String generateHTML(String template, String localeCode, boolean isDraft, Object... args) {
        Map<String, Object> model = createModel(localeCode, isDraft, args);

        return freemarkerGenerator.generate(template, model, new Locale(localeCode), StandardCharsets.UTF_8.name());
    }

    private Map<String, Object> createModel(String localeCode, boolean isDraft, Object... args) {
        Map<String, Object> model = new HashMap<>();
        model.put("args", args);
        model.put("isDraft", isDraft);
        model.put("messages", getMessages(localeCode, resourceFolder + COMMON_MESSAGES_MESSAGES_DIR));
        model.put("resourceBundle", new ResourceBundleMethod());
        return model;
    }

    private Properties getMessages(String locale, String commonMessagesDir) {
        Properties pMessages = messages.get(locale);
        if (pMessages == null) {
            pMessages = ReportMessagesUtil.readMessages(commonMessagesDir, locale);
            messages.put(locale, pMessages);
        }
        return pMessages;
    }
}
