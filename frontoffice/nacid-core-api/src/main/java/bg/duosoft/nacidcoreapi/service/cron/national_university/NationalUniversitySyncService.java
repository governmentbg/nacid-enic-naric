package bg.duosoft.nacidcoreapi.service.cron.national_university;

import bg.duosoft.nacidcoreapi.integration.register.national_university.domain.NationalUniversityModel;
import bg.duosoft.nacidfrontofficedto.nomenclature.NationalUniversityDTO;

public interface NationalUniversitySyncService {
    void syncNationalUniversityInfo(NationalUniversityModel registerUniversity, NationalUniversityDTO nationalUniversity);
}
