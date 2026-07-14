package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.RecognizedProfessionRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecognizedProfessionService {
    private final RecognizedProfessionRepository recognizedProfessionRepository;

    public List<String> selectRecognizedProfessions(AutocompleteViewFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        return recognizedProfessionRepository.selectRecognizedProfessions(filter);
    }
}
