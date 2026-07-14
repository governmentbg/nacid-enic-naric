package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.impl;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity.SubjectsGradesStatisticEntity;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.repository.SubjectsGradesStatisticRepository;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.SubjectsGradesStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectsGradesStatisticServiceImpl implements SubjectsGradesStatisticService {

    private final SubjectsGradesStatisticRepository subjectsGradesStatisticRepository;

    @Override
    public void saveSubjectsGradesStatistic(List<SubjectsGradesStatisticEntity> subjectsGradesStatisticEntities) {
        this.subjectsGradesStatisticRepository.saveAll(subjectsGradesStatisticEntities);
    }
}
