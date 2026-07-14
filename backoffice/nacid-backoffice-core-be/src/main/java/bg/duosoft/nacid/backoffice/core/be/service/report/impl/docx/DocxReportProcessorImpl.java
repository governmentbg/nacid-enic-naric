package bg.duosoft.nacid.backoffice.core.be.service.report.impl.docx;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.DocumentTypeService;
import bg.duosoft.nacid.backoffice.core.be.service.report.impl.FieldSqlExecutor;
import bg.duosoft.nacid.backoffice.core.be.service.report.impl.GroupSqlExecutor;
import bg.duosoft.nacid.backoffice.core.be.service.report.impl.ReportProcessorBaseImpl;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.GenerateReportsResult;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.DocCategory;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReportType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDetailDTO;
import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;
import bg.duosoft.nacidminiodto.util.FileConstants;
import bg.duosoft.nacidminioservices.service.FileStoreService;
import bg.duosoft.nacidshareddata.util.MimeTypeUtils;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static bg.duosoft.nacidminiodto.util.FileConstants.TEMP_ROOT_DIRECTORY;

/**
 * User: ggeorgiev
 * Date: 01.11.2022
 * Time: 17:11
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DocxReportProcessorImpl extends ReportProcessorBaseImpl {

    private final ReportServiceFieldExecutor fieldExecutor;
    private final ReportServiceGroupExecutor groupExecutor;
    private final FieldSqlExecutor fieldSqlExecutor;
    private final GroupSqlExecutor groupSqlExecutor;
    private final FileStoreService fileStoreService;
    private final DocumentTypeService documentTypeService;


    public List<String> getDocumentGroupAndFieldNames(String templateName) {
        List<String> result = new ArrayList<>();
        Document document = createDocument(templateName);
        String[] fieldNames = document.getMailMerge().getMergeFieldNames();
        String[] groupNames = document.getMailMerge().getMergeGroupNames();

        result.addAll(groupSqlExecutor.getGroupNames(fieldNames, groupNames));
        result.addAll(fieldSqlExecutor.getFieldNames(fieldNames, groupNames));
        return result;
    }

    @Override
    public byte[] generateReport(ReportType reportType, String templateName, Map<String, Object> sqlParams, Map<String, Object> customValues) {

        if (customValues == null) {
            customValues = new HashMap<>();
        }
        if (sqlParams == null) {
            sqlParams = new HashMap<>();
        }

        Document document = createDocument(templateName);
        document.isUpdateFields(true);
        String[] groupNames = document.getMailMerge().getMergeGroupNames();
        String[] fieldNames = document.getMailMerge().getMergeFieldNames();
        document.getMailMerge().setHideEmptyGroup(true);

        //zadyljitelno pyrvo se izpylnqvat grupite, inache stavat typotii. Ako pyrvo se izpylnqt field-ovete, grupite se tretirat kato fields, i za tqh sy6to se pravi opit da se vikne mailMergeField - tova e nqkakva typotiq na spireDoc.
        groupExecutor.process(document, fieldNames, groupNames, customValues, sqlParams);
        fieldExecutor.process(document, fieldNames, groupNames, customValues, sqlParams);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        document.saveToStream(os, getFileFormat(reportType));
        return os.toByteArray();
    }


    private FileFormat getFileFormat(ReportType reportType) {
        return switch (reportType) {
            case DOCX -> FileFormat.Docx;
            case PDF -> FileFormat.PDF;
            default -> throw new RuntimeException("Unknown report type " + reportType);
        };
    }

    private Document createDocument(String templateName) {
        Document document = new Document();
        Path p = getTemplatePath(templateName);
        document.loadFromFile(p.toString());
        return document;
    }

    @Override
    public GenerateReportsResult generateApplicationReports(ReportType reportType, Integer documentTypeId, List<Integer> applicationIds, Map<Integer, Map<String, Object>> customValues, Map<Integer, Map<String, String>> metadata) {
        if (reportType != ReportType.DOCX) {
            throw new RuntimeException("The only supported reportType (for now) is docx!");
        }
        Map<String, Document> mergedDocuments = new HashMap<>();
        Map<Integer, List<GenerateReportsResult.ReportStoreDetailAndDocumentDetail>> generatedReports = new LinkedHashMap<>();
        for (Integer applicationId : applicationIds) {
            DocumentTypeDTO documentType = documentTypeService.selectDocumentType(documentTypeId, DocCategory.APP_ATTACHMENTS, applicationId);
            List<DocumentTypeDetailDTO> dtds = documentType == null ? null : documentType.getDetails();
            List<GenerateReportsResult.ReportStoreDetailAndDocumentDetail> generatedReportsPerApplication = new ArrayList<>();
            if (ObjectUtils.isEmpty(dtds)) {
                throw new RuntimeException("Cannot determine template for document type id = " + documentTypeId + " and application id = " + applicationId);
            }
            for (DocumentTypeDetailDTO dtd : dtds) {
                String templateName = dtd.getTemplate();
                byte[] report = generateApplicationReport(reportType, templateName, applicationId, null, customValues == null ? null : customValues.get(applicationId));
                generatedReportsPerApplication.add(new GenerateReportsResult.ReportStoreDetailAndDocumentDetail(storeFile(report, reportType, templateName, metadata == null ? null : metadata.get(applicationId)), dtd));
                mergedDocuments.computeIfAbsent(templateName, (k) -> new Document());
                mergedDocuments.get(templateName).insertTextFromStream(new ByteArrayInputStream(report), getFileFormat(reportType));
            }
            generatedReports.put(applicationId, generatedReportsPerApplication);
        }

        Map<String, FileStoreEntryBaseDTO> mergedDocumentContents = mergedDocuments.entrySet().stream().collect(Collectors.toMap(r -> r.getKey(), r -> {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            r.getValue().saveToStream(bos, getFileFormat(reportType));
            String templateName = r.getKey();
            Path p = Paths.get(templateName);
            String tn = "merged-" + p.getFileName().toString();
            return storeFile(bos.toByteArray(), reportType, tn, null);
        }));

        return new GenerateReportsResult(mergedDocumentContents, generatedReports);
    }

    private FileStoreEntryBaseDTO storeFile(byte[] content, ReportType reportType, String templateName, Map<String, String> metadata) {
        FileStoreEntryBaseDTO d = new FileStoreEntryBaseDTO();
        d.setRootDirectory(TEMP_ROOT_DIRECTORY);

        d.setContent(content);
        d.setFileSize((long) content.length);

        Path p = Paths.get(templateName);
        String fileName = FilenameUtils.removeExtension(p.getFileName().toString()) + "." + reportType.getExtension();

        d.setFileName(fileName);
        d.setContentType(MimeTypeUtils.guessMimeFromBytes(content, fileName));
        d.setRelativePath("reports");

        if (Objects.nonNull(metadata)) {
            d.setAdditionalMetadata(metadata);
        }

        return fileStoreService.saveNewFile(FileConstants.FILE_GROUP_NOLIMIT, "-", d);
    }
}
