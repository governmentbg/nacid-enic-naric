package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.accept;

import lombok.Data;

@Data
public class RudiAcceptBaseDTO {

    private Integer applicantId;
    private Integer representativeId;
    private Integer representativeCompanyId;
    private Boolean representativeCompanyFlag;
    private Integer contactAddressId;
    private Integer baseUniversityId;

}
