package bg.duosoft.nacidcoreapi.service.cron.national_university.impl;

import bg.duosoft.nacidcoreapi.integration.register.national_university.domain.NationalUniversityModel;
import bg.duosoft.nacidcoreapi.repository.nomenclatures.NationalUniversityRepository;
import bg.duosoft.nacidcoreapi.service.cron.national_university.NationalUniversitySyncService;
import bg.duosoft.nacidcoredata.mapper.nomenclature.NationalUniversityMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.NationalUniversityDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.SettlementDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NationalUniversitySyncServiceImpl implements NationalUniversitySyncService {

    private final NationalUniversityRepository nationalUniversityRepository;
    private final NationalUniversityMapper nationalUniversityMapper;

    @Override
    public void syncNationalUniversityInfo(NationalUniversityModel registerUniversity, NationalUniversityDTO nationalUniversity) {
        if (Objects.nonNull(nationalUniversity)) {
            fillInformation(registerUniversity, nationalUniversity);
            nationalUniversityRepository.save(nationalUniversityMapper.toEntity(nationalUniversity));
        } else {
            NationalUniversityDTO newUniversity = new NationalUniversityDTO();
            newUniversity.setId(registerUniversity.getUic());
            fillInformation(registerUniversity, newUniversity);
            nationalUniversityRepository.save(nationalUniversityMapper.toEntity(newUniversity));
        }
    }

    private void fillInformation(NationalUniversityModel registerUniversity, NationalUniversityDTO nationalUniversity) {
        if (Objects.isNull(nationalUniversity.getSettlement())) {
            nationalUniversity.setSettlement(new SettlementDTO());
        }

        String settlementId = Objects.nonNull(registerUniversity.getSettlement()) ? registerUniversity.getSettlement().getCode() : null;
        nationalUniversity.getSettlement().setId(fillNotEmptyRegisterValue(settlementId, nationalUniversity.getSettlement().getId()));
        nationalUniversity.setName(fillNotEmptyRegisterValue(registerUniversity.getName(), nationalUniversity.getName()));
        nationalUniversity.setNameEn(fillNotEmptyRegisterValue(registerUniversity.getNameAlt(), nationalUniversity.getNameEn()));
        nationalUniversity.setAddress(fillNotEmptyRegisterValue(registerUniversity.getAddress(), nationalUniversity.getAddress()));
        nationalUniversity.setAddressEn(fillNotEmptyRegisterValue(registerUniversity.getAddressAlt(), nationalUniversity.getAddressEn()));
        nationalUniversity.setZipCode(fillNotEmptyRegisterValue(registerUniversity.getPostCode(), nationalUniversity.getZipCode()));
        nationalUniversity.setWebsite(fillNotEmptyRegisterValue(registerUniversity.getWebPageUrl(), nationalUniversity.getWebsite()));
        nationalUniversity.setIsActive(true);
    }

    private String fillNotEmptyRegisterValue(String registerValue, String databaseValue) {
        if (StringUtils.hasText(registerValue)) {
            return registerValue;
        } else {
            return databaseValue;
        }
    }
}
