package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.ExperienceProfessionRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceProfessionService {
    private final ExperienceProfessionRepository experienceProfessionRepository;

    public List<String> selectExperienceProfessions(AutocompleteViewFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        return experienceProfessionRepository.selectExperienceProfessions(filter);
    }
}
