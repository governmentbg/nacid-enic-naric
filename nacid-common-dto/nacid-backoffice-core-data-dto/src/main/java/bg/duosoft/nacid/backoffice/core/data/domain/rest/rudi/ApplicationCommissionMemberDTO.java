package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CommissionMemberPositionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LegalReasonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.Data;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 05.01.2023
 * Time: 15:27
 */
@Data
public class ApplicationCommissionMemberDTO {
    private Integer id;
    private CommissionMemberDTO commissionMember;
    private String notes;
    private String courseContent;
    private String qualification;
    private String previousBoardDecisions;
    private String similarBulgarianPrograms;
    private ReferenceDataDTO eduLevel;
    private CommissionMemberPositionDTO commissionMemberPosition;
    private LegalReasonDTO legalReason;
    private Boolean processStatus;
    private List<ApplicationCommissionMemberSpecialityDTO> applicationCommissionMemberSpecialities;
}
