package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.OriginalEduLevelRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.OriginalEduLevelTranslationRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OriginalEduLevelsService {
    private final OriginalEduLevelRepository originalEduLevelRepository;
    private final OriginalEduLevelTranslationRepository originalEduLevelTranslationRepository;

    public List<String> selectOriginalEduLevels(AutocompleteViewFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        return originalEduLevelRepository.selectOriginalEduLevels(filter);
    }

    public List<String> selectOriginalEduLevelTranslations(AutocompleteViewFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        return originalEduLevelTranslationRepository.selectOriginalEduLevelTranslations(filter);
    }
}
