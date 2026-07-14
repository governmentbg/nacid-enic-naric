package bg.duosoft.nacid.backoffice.core.data.domain.rest.se;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LegalReasonDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 21.08.2025
 * Time: 16:37
 */
@Getter
@Setter
@EqualsAndHashCode
public class SEApplicationDTO {
    private ApplicationDTO application;
    private String multipleApplicationId;
    private SETrainingCourseDTO trainingCourse;
    private Boolean originalDocumentWaitingFlag;

    private List<SEApplicationRecognitionPurposeDTO> applicationRecognitionPurposes;
    private LegalReasonDTO legalReason;
    private String motives;

}
