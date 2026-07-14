package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.OriginalSpecialityRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OriginalSpecialityService {
    private final OriginalSpecialityRepository originalSpecialityRepository;

    public List<String> selectOriginalSpecialities(AutocompleteViewFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        return originalSpecialityRepository.selectOriginalSpecialities(filter);
    }
}
