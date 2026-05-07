package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 10.11.2025
 * Time: 15:49
 */
@Data
@NoArgsConstructor
public class DocumentTypeAbdocsConfigDTO {
    private Integer id;
    private ApplicationTypeDTO applicationType;
    private ApplicationSubtypeDTO applicationSubtype;
    private Integer abdocsDocTypeId;
    private Boolean abdocsAutoInsertFlag;
    private String abdocsTaskResult;
    private String abdocsTaskUser;
    private String abdocsDocFrom;
    private String abdocsDocEditor;
}
