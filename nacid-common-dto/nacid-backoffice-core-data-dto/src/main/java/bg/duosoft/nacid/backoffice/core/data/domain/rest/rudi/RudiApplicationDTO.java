package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LegalReasonDTO;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class RudiApplicationDTO implements Serializable {

    private ApplicationDTO application;
    private String bgAddressOwner;
    private Boolean representativeAuthorizedFlag;
    private String submittedDocs;
    private TrainingCourseDTO trainingCourse;
    private List<ApplicationCommissionMemberDTO> applicationCommissionMembers;
    private List<ApplicationCommissionMemberStatementDTO> applicationCommissionMemberStatements;
    private List<ApplicationRecognitionPurposeDTO> applicationRecognitionPurposes;
    private List<CommissionApplicationDTO> commissionApplications;
    private List<ApplicationRecognizedSpecialityDTO> recognizedSpecialities;
    private ApplicationRecognizedDetailsDTO applicationRecognizedDetails;
    private SarApplicationDTO sarApplication;
    private LegalReasonDTO legalReason;
}
