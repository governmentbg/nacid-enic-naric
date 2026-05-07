package bg.duosoft.nacid.backoffice.core.data.domain.rest;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDetailDTO;
import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * User: ggeorgiev
 * Date: 18.04.2023
 * Time: 13:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateReportsResult {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReportStoreDetailAndDocumentDetail {
        private FileStoreEntryBaseDTO storeLocation;
        private DocumentTypeDetailDTO documentDetail;
    }

    private Map<String, FileStoreEntryBaseDTO> mergedReportsByTemplateName;
    private Map<Integer, List<ReportStoreDetailAndDocumentDetail>> reports;
}
