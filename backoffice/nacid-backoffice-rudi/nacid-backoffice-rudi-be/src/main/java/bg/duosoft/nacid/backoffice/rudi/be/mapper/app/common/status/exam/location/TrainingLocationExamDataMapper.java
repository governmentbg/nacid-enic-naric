package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.exam.location;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.TrainingLocationSimpleDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.UniversitySimpleDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.training_location.TrainingLocationExamSectionDTO;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class TrainingLocationExamDataMapper {

    @Mapping(target = "applicationId", source = "application.id")
    @Mapping(target = "isLegitimate", source = "trainingCourse.trainingLocationExamination.isLegitimate", defaultValue = "false")
    public abstract TrainingLocationExamSectionDTO toTrainingLocationExamSection(RudiApplicationDTO application);

    @AfterMapping
    public void afterToTrainingLocationExamSection(RudiApplicationDTO source, @MappingTarget TrainingLocationExamSectionDTO target) {
        TrainingCourseDTO trainingCourse = source.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            List<TrainingCourseUniversityDTO> trainingCourseUniversities = trainingCourse.getTrainingCourseUniversities();
            if (!CollectionUtils.isEmpty(trainingCourseUniversities)) {
                List<TrainingCourseUniversityDTO> sortedUniversities = trainingCourseUniversities.stream().sorted(Comparator.comparing(TrainingCourseUniversityDTO::getOrdNum)).toList();

                List<UniversitySimpleDTO> simpleUniversities = new ArrayList<>();
                for (TrainingCourseUniversityDTO trainingCourseUniversity : sortedUniversities) {
                    UniversityDTO university = trainingCourseUniversity.getUniversity();
                    UniversitySimpleDTO universitySimple = UniversitySimpleDTO.builder().id(university.getId()).bgName(university.getBgName()).country(university.getCountry()).address(university.getAddress()).build();
                    simpleUniversities.add(universitySimple);
                }
                target.setUniversities(simpleUniversities);
                target.setUniversityIds(sortedUniversities.stream().map(university -> university.getUniversity().getId()).collect(Collectors.toList()));
            }

            List<TrainingLocationDTO> trainingLocations = trainingCourse.getTrainingLocations();

            if (!CollectionUtils.isEmpty(trainingLocations)) {
                List<TrainingLocationSimpleDTO> simpleTrainingLocations = new ArrayList<>();
                for (TrainingLocationDTO location : trainingLocations) {
                    TrainingInstitutionDTO examinationTrainingInstitution = location.getExaminationTrainingInstitution();

                    TrainingLocationSimpleDTO simpleLocation = TrainingLocationSimpleDTO.builder()
                            .id(location.getId())
                            .country(location.getCountry())
                            .city(location.getCity())
                            .isNotUniInstitution(Objects.nonNull(examinationTrainingInstitution))
                            .examinationTrainingInstitutionId(Objects.nonNull(examinationTrainingInstitution) ? examinationTrainingInstitution.getId() : null)
                            .build();

                    simpleTrainingLocations.add(simpleLocation);
                }
                target.setTrainingLocations(simpleTrainingLocations);
            }
        }
    }

    @InheritInverseConfiguration(name = "toTrainingLocationExamSection")
    public abstract void overrideApplicationData(TrainingLocationExamSectionDTO source, @MappingTarget RudiApplicationDTO target);

    @AfterMapping
    public void afterOverride(TrainingLocationExamSectionDTO source, @MappingTarget RudiApplicationDTO target) {
        overrideSourceData(source);

        TrainingCourseDTO trainingCourse = target.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            List<TrainingLocationSimpleDTO> trainingLocations = source.getTrainingLocations();

            if (!CollectionUtils.isEmpty(trainingLocations)) {
                for (TrainingLocationSimpleDTO simpleLocation : trainingLocations) {
                    TrainingLocationDTO trainingLocation = trainingCourse.getTrainingLocations().stream().filter(location -> location.getId().equals(simpleLocation.getId())).findFirst().orElse(null);
                    if (Objects.nonNull(trainingLocation)) {
                        if (simpleLocation.getIsNotUniInstitution()) {
                            trainingLocation.setExaminationTrainingInstitution(new TrainingInstitutionDTO(simpleLocation.getExaminationTrainingInstitutionId()));
                        } else {
                            trainingLocation.setExaminationTrainingInstitution(null);
                        }
                    }
                }
            }
        }
    }

    public void overrideSourceData(TrainingLocationExamSectionDTO source) {
        List<TrainingLocationSimpleDTO> trainingLocations = source.getTrainingLocations();
        if (!CollectionUtils.isEmpty(trainingLocations)) {
            for (TrainingLocationSimpleDTO simpleLocation : trainingLocations) {
                if (simpleLocation.getIsNotUniInstitution() && Objects.isNull(simpleLocation.getExaminationTrainingInstitutionId())) {
                    simpleLocation.setIsNotUniInstitution(false);
                }
            }
        }
    }
}
