package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.ImiCorrespondenceRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImiCorrespondenceService {
    private final ImiCorrespondenceRepository imiCorrespondenceRepository;

    public List<String> selectImiCorrespondences(AutocompleteViewFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        return imiCorrespondenceRepository.selectImiCorrespondences(filter);
    }
}
