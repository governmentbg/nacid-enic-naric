package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.SdkQualificationRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SdkQualificationService {
    private final SdkQualificationRepository sdkQualificationRepository;

    public List<String> selectSdkQualifications(AutocompleteViewFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        return sdkQualificationRepository.selectSdkQualifications(filter);
    }
}
