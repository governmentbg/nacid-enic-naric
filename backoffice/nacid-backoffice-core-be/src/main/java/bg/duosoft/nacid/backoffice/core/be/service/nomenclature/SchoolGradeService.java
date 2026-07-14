package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.SchoolGradeRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolGradeService {
    private final SchoolGradeRepository schoolGradeRepository;

    public List<String> selectSchoolGrades(AutocompleteViewFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        return schoolGradeRepository.selectSchoolGrades(filter);
    }
}
