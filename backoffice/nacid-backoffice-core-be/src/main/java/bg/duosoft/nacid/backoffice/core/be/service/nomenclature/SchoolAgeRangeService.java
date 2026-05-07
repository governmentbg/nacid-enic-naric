package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.SchoolAgeRangeRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolAgeRangeService {
    private final SchoolAgeRangeRepository schoolGradeRepository;

    public List<String> selectSchoolAgeRanges(AutocompleteViewFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        return schoolGradeRepository.selectSchoolAgeRanges(filter);
    }
}
