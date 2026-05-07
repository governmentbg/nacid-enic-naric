package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.education;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LanguageDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.education.RudiEduDataBaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DocrecEduDataDTO extends RudiEduDataBaseDTO {

    private Boolean graduationWayDissertation;

    private String thesisTopic;
    private String thesisTopicEn;
    private LocalDate thesisDefenceDate;
    private Integer thesisBibliography;
    private Integer thesisVolume;
    private String thesisAnnotation;
    private String thesisAnnotationEn;
    private LanguageDTO thesisLanguage;
    private String scientificSupervisor;
    private String scientificSupervisorEn;
    private String reviewers;
    private String reviewersEn;
    private String juryChair;
    private String juryChairEn;
    private String juryMembers;
    private String juryMembersEn;

}
