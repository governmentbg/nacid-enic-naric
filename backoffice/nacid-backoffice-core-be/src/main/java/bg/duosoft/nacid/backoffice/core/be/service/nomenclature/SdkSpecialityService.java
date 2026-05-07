package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.SdkSpecialityRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SdkSpecialityService {
    private final SdkSpecialityRepository sdkSpecialityRepository;

    public List<String> selectSdkSpecialities(AutocompleteViewFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        return sdkSpecialityRepository.selectSdkSpecialities(filter);
    }
}
