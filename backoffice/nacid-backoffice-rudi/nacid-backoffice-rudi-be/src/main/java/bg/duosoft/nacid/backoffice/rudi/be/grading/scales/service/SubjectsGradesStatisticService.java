package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service;


import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity.SubjectsGradesStatisticEntity;

import java.util.List;

public interface SubjectsGradesStatisticService {

    void saveSubjectsGradesStatistic(List<SubjectsGradesStatisticEntity> subjectsGradesStatisticEntities);
}
