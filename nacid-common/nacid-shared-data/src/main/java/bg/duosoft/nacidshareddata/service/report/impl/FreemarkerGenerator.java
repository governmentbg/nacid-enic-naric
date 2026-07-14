package bg.duosoft.nacidshareddata.service.report.impl;

import bg.duosoft.nacidshareddata.exception.ReportException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

@Slf4j
public class FreemarkerGenerator {

	private static final int BUFFER_SIZE = 16 * 1024;// based on template

	private final Configuration configuration;

	private final int bufferSize;

	public FreemarkerGenerator(Configuration configuration, int bufferSize) {
		if (configuration == null) {
			throw new IllegalArgumentException("configuration cannot be null!");
		}
		if (bufferSize <= 0) {
			throw new IllegalArgumentException("buffersize should be positive");
		}

		this.configuration = configuration;
		this.bufferSize = bufferSize;
	}

	public FreemarkerGenerator(Configuration configuration) {
		this(configuration, BUFFER_SIZE);
	}

	public String generate(String templateName, Map<String, Object> model,
			Locale locale, String encoding) {

		if (templateName == null) {
			throw new IllegalArgumentException("templateName cannot be null!");
		}

		if (model == null) {
			throw new IllegalArgumentException("model cannot be null!");
		}

		if (locale == null) {
			throw new IllegalArgumentException("locale cannot be null!");
		}

		try {
			Template template = configuration.getTemplate(templateName, locale,
					encoding);

			StringWriter writer = new StringWriter(bufferSize);
			template.process(model, writer);

			return writer.toString();
		} catch (Exception e) {
			throw new ReportException("Failed to generate template content", e);
		}
	}
}
