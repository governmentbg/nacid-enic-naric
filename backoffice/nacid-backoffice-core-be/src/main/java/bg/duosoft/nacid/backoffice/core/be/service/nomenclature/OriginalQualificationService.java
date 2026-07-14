package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.OriginalQualificationRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OriginalQualificationService {
    private final OriginalQualificationRepository originalQualificationRepository;

    public List<String> selectOriginalQualifications(AutocompleteViewFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        return originalQualificationRepository.selectOriginalQualifications(filter);
    }
}
