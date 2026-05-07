package bg.duosoft.nacidfrontofficedto.services.docdegrees;

import bg.duosoft.nacidfrontofficedto.nomenclature.LanguageDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ProfGroupDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.PreviousUniversityDiplomaDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.RudiEducationDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.WithRecognitionCategory;
import bg.duosoft.nacidfrontofficedto.services.common.education.WithPreviousUniversityDiploma;
import bg.duosoft.nacidfrontofficedto.utils.constants.DTOConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 28.07.2022
 * Time: 11:25
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocEducationDetailsDTO extends RudiEducationDetailsDTO implements WithPreviousUniversityDiploma, WithRecognitionCategory {

    private ProfGroupDTO gainedLevelProfGroup;

    private PreviousUniversityDiplomaDTO previousUniversityDiploma;

    private String dissertationTheme;
    private String dissertationThemeEn;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = DTOConstants.DATE_FORMAT)
    private LocalDate dissertationDate;
    private LanguageDTO dissertationLanguage;
    private String dissertationBiblioTitlesCount;
    private String dissertationPagesCount;
    private String dissertationAnnotation;
    private String dissertationAnnotationEn;
    private String scientificSupervisor;
    private String scientificSupervisorEn;
    private String reviewers;
    private String reviewersEn;
    private String juryChair;
    private String juryChairEn;
    private String juryMembers;
    private String juryMembersEn;
    private ReferenceDataDTO recognitionCategory;
}
