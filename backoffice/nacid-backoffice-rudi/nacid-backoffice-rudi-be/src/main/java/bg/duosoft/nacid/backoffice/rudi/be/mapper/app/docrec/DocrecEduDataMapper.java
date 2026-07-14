package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.docrec;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.GraduationWayType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseGraduationWayDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.education.DocrecEduDataDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.education.EduDataMapperBase;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.education.EduDataMapperConfig;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.education.EduDataMapperUtils.fillPrevDiplomaUniversity;
import static bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.education.EduDataMapperUtils.graduationWaySectionToTrainingCourse;

@Mapper(componentModel = "spring", config = EduDataMapperConfig.class)
public abstract class DocrecEduDataMapper extends EduDataMapperBase<DocrecEduDataDTO> {

    @InheritConfiguration(name = "baseEducationDataSectionMapping")
    @Mapping(target = "thesisTopic", source = "trainingCourse.thesisTopic")
    @Mapping(target = "thesisTopicEn", source = "trainingCourse.thesisTopicEn")
    @Mapping(target = "thesisDefenceDate", source = "trainingCourse.thesisDefenceDate")
    @Mapping(target = "thesisBibliography", source = "trainingCourse.thesisBibliography")
    @Mapping(target = "thesisVolume", source = "trainingCourse.thesisVolume")
    @Mapping(target = "thesisAnnotation", source = "trainingCourse.thesisAnnotation")
    @Mapping(target = "thesisAnnotationEn", source = "trainingCourse.thesisAnnotationEn")
    @Mapping(target = "thesisLanguage", source = "trainingCourse.thesisLanguage")
    @Mapping(target = "scientificSupervisor", source = "trainingCourse.scientificSupervisor")
    @Mapping(target = "scientificSupervisorEn", source = "trainingCourse.scientificSupervisorEn")
    @Mapping(target = "reviewers", source = "trainingCourse.reviewers")
    @Mapping(target = "reviewersEn", source = "trainingCourse.reviewersEn")
    @Mapping(target = "juryChair", source = "trainingCourse.juryChair")
    @Mapping(target = "juryChairEn", source = "trainingCourse.juryChairEn")
    @Mapping(target = "juryMembers", source = "trainingCourse.juryMembers")
    @Mapping(target = "juryMembersEn", source = "trainingCourse.juryMembersEn")
    public abstract DocrecEduDataDTO toEducationDataSection(RudiApplicationDTO application);


    @InheritInverseConfiguration(name = "toEducationDataSection")
    public abstract void overrideApplicationData(DocrecEduDataDTO source, @MappingTarget RudiApplicationDTO target);


    @AfterMapping
    public void afterOverride(DocrecEduDataDTO source, @MappingTarget RudiApplicationDTO target) {
        commonAfterOverride(source, target);

        TrainingCourseDTO trainingCourse = target.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {

            if (Objects.isNull(source.getThesisLanguage()) || Objects.isNull(source.getThesisLanguage().getId())) {
                trainingCourse.setThesisLanguage(null);
            }

            List<TrainingCourseGraduationWayDTO> graduationWays = target.getTrainingCourse().getGraduationWays();
            if (Objects.isNull(graduationWays)) {
                graduationWays = new ArrayList<>();
            }
            List<TrainingCourseGraduationWayDTO> newGraduationWays = new ArrayList<>();
            graduationWaySectionToTrainingCourse(graduationWays, newGraduationWays, source.getTrainingCourseId(), source.getGraduationWayDissertation(), GraduationWayType.DISSERTATION.code(), source.getGraduationWayNotes());
            graduationWaySectionToTrainingCourse(graduationWays, newGraduationWays, source.getTrainingCourseId(), source.getGraduationWayOther(), GraduationWayType.OTHER.code(), source.getGraduationWayNotes());
            target.getTrainingCourse().setGraduationWays(newGraduationWays);
        }
    }

    @AfterMapping
    public void afterToEducationDataSection(RudiApplicationDTO source, @MappingTarget DocrecEduDataDTO target) {
        commonAfterOverrideInverse(source, target);
        if (Objects.nonNull(source.getTrainingCourse())) {
            fillPrevDiplomaUniversity(source, target);

            target.setGraduationWayDissertation(source.getTrainingCourse().getGraduationWays().stream().anyMatch(o -> o.getGraduationWay().getId().equals(GraduationWayType.DISSERTATION.code())));
            Optional<TrainingCourseGraduationWayDTO> otherGraduationWay = source.getTrainingCourse().getGraduationWays().stream().filter(o -> o.getGraduationWay().getId().equals(GraduationWayType.OTHER.code())).findFirst();
            target.setGraduationWayOther(otherGraduationWay.isPresent());
            target.setGraduationWayNotes(otherGraduationWay.isPresent() ? otherGraduationWay.get().getNotes() : "");
        }
    }

}
