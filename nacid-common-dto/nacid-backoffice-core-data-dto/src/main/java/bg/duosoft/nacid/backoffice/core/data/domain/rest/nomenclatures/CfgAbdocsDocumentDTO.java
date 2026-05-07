package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import lombok.Data;

@Data
public class CfgAbdocsDocumentDTO {
    private String id;
    private String name;
    private Integer docTypeId;
    private Integer docRegistrationTypeId;
    private String docSubject;
    private String docTo;
}
