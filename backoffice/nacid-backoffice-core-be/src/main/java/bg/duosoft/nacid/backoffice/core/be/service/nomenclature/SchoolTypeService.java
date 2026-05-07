package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.SchoolTypeRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolTypeService {
    private final SchoolTypeRepository schoolTypeRepository;

    public List<String> selectSchoolTypes(AutocompleteViewFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        return schoolTypeRepository.selectSchoolTypes(filter);
    }
}
