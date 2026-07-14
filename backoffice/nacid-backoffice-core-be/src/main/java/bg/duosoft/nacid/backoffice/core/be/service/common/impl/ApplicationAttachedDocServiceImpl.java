package bg.duosoft.nacid.backoffice.core.be.service.common.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.common.ApplicationAttachedDocRepository;
import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationAttachedDocService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationAttachedDocServiceImpl implements ApplicationAttachedDocService {

    private final ApplicationAttachedDocRepository repository;

    @Override
    public String selectDocflowIdById(Integer id) {
        return repository.selectDocflowIdById(id);
    }
}
