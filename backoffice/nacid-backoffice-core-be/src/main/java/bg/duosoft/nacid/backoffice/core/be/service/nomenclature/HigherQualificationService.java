package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.HigherQualificationRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HigherQualificationService {
    private final HigherQualificationRepository higherQualificationRepository;

    public List<String> selectHigherQualifications(AutocompleteViewFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        return higherQualificationRepository.selectHigherQualifications(filter);
    }
}
