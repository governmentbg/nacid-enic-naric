package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.rudi.be.repository.ApplicationRecognizedDetailsRepository;
import bg.duosoft.nacid.backoffice.rudi.be.service.ApplicationRecognizedDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationRecognizedDetailsServiceImpl implements ApplicationRecognizedDetailsService {
    private final ApplicationRecognizedDetailsRepository applicationRecognizedDetailsRepository;
    @Override
    public List<String> selectAllDistinctQualifications() {
        return applicationRecognizedDetailsRepository.selectAllDistinctQualifications();
    }
}
