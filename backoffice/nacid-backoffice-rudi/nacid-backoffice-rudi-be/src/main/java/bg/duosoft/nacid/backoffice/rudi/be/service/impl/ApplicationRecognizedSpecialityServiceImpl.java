package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.rudi.be.repository.ApplicationRecognizedSpecialityRepository;
import bg.duosoft.nacid.backoffice.rudi.be.service.ApplicationRecognizedSpecialityService;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationRecognizedSpecialityServiceImpl implements ApplicationRecognizedSpecialityService {

    private final ApplicationRecognizedSpecialityRepository specialityRepository;

    @Override
    public List<String> selectAllDistinctSpecialities() {
        return specialityRepository.selectAllDistinctSpecialities();
    }
}
