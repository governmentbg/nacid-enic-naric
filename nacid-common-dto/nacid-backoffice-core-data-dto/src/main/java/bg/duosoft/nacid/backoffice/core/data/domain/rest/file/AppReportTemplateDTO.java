package bg.duosoft.nacid.backoffice.core.data.domain.rest.file;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReportType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDetailDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppReportTemplateDTO implements Serializable {
    private List<Template> templates;
    private ReportType reportType;
    private Integer applicationId;
    private Integer documentTypeId;
    private Integer commissionMemberId;
    private String rootDirectory;
    private String relativePath;
    private String fileGroup;
    private String pointer;
    private Integer attachmentId;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Template {
       private String template;
       private ReferenceDataDTO defaultAttachmentVisibility;
    }
}
