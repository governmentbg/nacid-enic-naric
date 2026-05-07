package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.SchoolSubjectRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolSubjectService {
    private final SchoolSubjectRepository schoolSubjectRepository;

    public List<String> selectSchoolSubjects(AutocompleteViewFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        return schoolSubjectRepository.selectSchoolSubjects(filter);
    }
}
