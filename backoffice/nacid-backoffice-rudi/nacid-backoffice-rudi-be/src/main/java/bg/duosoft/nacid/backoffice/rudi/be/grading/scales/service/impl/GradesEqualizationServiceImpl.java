package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.impl;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.request.DiplomaDetailsDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.request.DiplomaSubjectDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.EqualizationSubjectDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity.RudiGradeEquivalenceEntity;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity.RudiGradingScaleDetailsEntity;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity.SubjectsGradesStatisticEntity;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.enums.ScaleTypeEnum;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.repository.GradingScaleDetailsRepository;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.GradesEqualizationService;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.SchoolSubjectService;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.SubjectsGradesStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GradesEqualizationServiceImpl implements GradesEqualizationService {

    private static final String NUMERIC_GRADE_PATTERN = "\\d+\\.?\\d*";

    private final GradingScaleDetailsRepository gradingScaleDetailsRepository;
    private final SubjectsGradesStatisticService subjectsGradesStatisticService;
    private final SchoolSubjectService schoolSubjectService;

    @Override
    public List<EqualizationSubjectDto> gradeEqualization(DiplomaDetailsDto diplomaDetails) {


        List<RudiGradingScaleDetailsEntity> gradingScaleDetailsEntities = new ArrayList<>();
        ScaleTypeEnum scaleType = null;

        if (diplomaDetails.getScalaId() != null) {
            gradingScaleDetailsEntities = fetchGradingScalesDetails(diplomaDetails.getScalaId());
            if (!gradingScaleDetailsEntities.isEmpty()) {
                scaleType = determineScaleType(gradingScaleDetailsEntities);
            }
        }


        List<SubjectsGradesStatisticEntity> statistics = new ArrayList<>();
        List<EqualizationSubjectDto> equalizationSubjects = new ArrayList<>();

        for (DiplomaSubjectDto subject : diplomaDetails.getSubjects()) {
            EqualizationSubjectDto equalizationSubjectDto = new EqualizationSubjectDto();

            equalizationSubjectDto.setSubjectName(subject.getSubjectName());
            equalizationSubjectDto.setSubjectGrade(cyrillicToLatin(subject.getSubjectGrade()));
            RudiGradeEquivalenceEntity gradeEquivalenceEntity;

            if (Objects.nonNull(scaleType)) {
                switch (Objects.requireNonNull(scaleType)) {
                    case LETTER -> gradeEquivalenceEntity = applyLetterGradeEquivalence(subject.getSubjectGrade(),
                            gradingScaleDetailsEntities);
                    case NUMERIC, PERCENT -> gradeEquivalenceEntity =
                            applyNumericOrPercentEquivalence(subject.getSubjectGrade(), gradingScaleDetailsEntities);
                    default -> gradeEquivalenceEntity = null;
                }
                Optional.ofNullable(gradeEquivalenceEntity)
                        .ifPresent(grade -> {
                            equalizationSubjectDto.setSubjectGradeBg(grade.getBulgarianGrade());
                            equalizationSubjectDto.setSubjectGradeBgText(grade.getBulgarianGradeText());
                        });
            }

            equalizationSubjects.add(equalizationSubjectDto);
            statistics.add(createStatisticEntry(equalizationSubjectDto));
        }

        subjectsGradesStatisticService.saveSubjectsGradesStatistic(statistics);
        schoolSubjectService.saveNonExistSchoolSubjects(diplomaDetails.getSubjects());
        return equalizationSubjects;
    }


    private List<RudiGradingScaleDetailsEntity> fetchGradingScalesDetails(Integer gradingScaleId) {
        return gradingScaleDetailsRepository.getGradingScaleDetailsByGradingScaleId(gradingScaleId);
    }

    private ScaleTypeEnum determineScaleType(List<RudiGradingScaleDetailsEntity> gradesEntities) {
        return gradesEntities.get(0).getGradingScale().getScaleType();
    }

    private SubjectsGradesStatisticEntity createStatisticEntry(EqualizationSubjectDto equalizationSubjectDto) {
        SubjectsGradesStatisticEntity entity = new SubjectsGradesStatisticEntity();
        entity.setSubject(equalizationSubjectDto.getSubjectName());
        entity.setOriginalGrade(equalizationSubjectDto.getSubjectGrade());
        entity.setEquatedGrade(equalizationSubjectDto.getSubjectGradeBg());
        entity.setCreatedDate(LocalDateTime.now());
        return entity;
    }

    private RudiGradeEquivalenceEntity applyLetterGradeEquivalence(String subjectGrade, List<RudiGradingScaleDetailsEntity> scaleGradeDetailsEntities) {

        RudiGradingScaleDetailsEntity matched = scaleGradeDetailsEntities.stream()
                .filter(sg -> Arrays.stream(sg.getSymbolValues()
                        .split(","))
                        .toList()
                        .contains(cyrillicToLatin(subjectGrade)))
                .findFirst()
                .orElse(null);

        return setEquatedGrade(matched);
    }

    private RudiGradeEquivalenceEntity applyNumericOrPercentEquivalence(String subjectGrade, List<RudiGradingScaleDetailsEntity> scaleGradeDetailsEntities) {
        if (!subjectGrade.matches(NUMERIC_GRADE_PATTERN)) {
            return null;
        }

        double value = Double.parseDouble(subjectGrade);
        RudiGradingScaleDetailsEntity matched = scaleGradeDetailsEntities.stream()
                .filter(sg -> sg.getMaxValue() >= value && value >= sg.getMinValue())
                .findFirst()
                .orElse(null);

        return setEquatedGrade(matched);
    }

    private RudiGradeEquivalenceEntity setEquatedGrade(RudiGradingScaleDetailsEntity scale) {
        if (scale == null) {
            return null;
        } else {
            return scale.getGradeEquivalence();
        }
    }

    private String cyrillicToLatin(String text){
        Map<String, String> CYRILLIC_TO_LATIN = Map.ofEntries(
                Map.entry("А", "A"),
                Map.entry("В", "B"),
                Map.entry("Б", "B"),
                Map.entry("С", "C"),
                Map.entry("Д", "D"),
                Map.entry("Е", "E"),
                Map.entry("Ф", "F"),
                Map.entry("Г", "G"),
                Map.entry("Н", "H"),
                Map.entry("К", "K"),
                Map.entry("Л", "L"),
                Map.entry("М", "M"),
                Map.entry("О", "O"),
                Map.entry("Р", "P"),
                Map.entry("Т", "T"),
                Map.entry("Х", "X"),
                Map.entry("У", "Y")
        );

        return Arrays.stream(text.trim().split(""))
                .map(l -> CYRILLIC_TO_LATIN.getOrDefault(l, l)).collect(Collectors.joining());
    }
}
