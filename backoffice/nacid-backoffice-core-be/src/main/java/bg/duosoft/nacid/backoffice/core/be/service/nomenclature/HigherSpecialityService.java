package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.HigherSpecialityRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HigherSpecialityService {
    private final HigherSpecialityRepository higherSpecialityRepository;

    public List<String> selectHigherSpecialities(AutocompleteViewFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        return higherSpecialityRepository.selectHigherSpecialities(filter);
    }
}
