package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.QualificationRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QualificationService {
    private final QualificationRepository qualificationRepository;

    public List<String> selectQualifications(AutocompleteViewFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        return qualificationRepository.selectQualifications(filter);
    }
}
