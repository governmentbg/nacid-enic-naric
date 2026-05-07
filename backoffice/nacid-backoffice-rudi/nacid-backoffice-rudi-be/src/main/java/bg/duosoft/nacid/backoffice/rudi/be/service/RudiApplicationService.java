package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;

import java.time.LocalDate;
import java.util.List;

public interface RudiApplicationService {

    Integer selectEfilingIdById(Integer id);

    RudiApplicationDTO selectById(Integer id);

    boolean existsByIdAndType(Integer id, ApplicationSubType subType);

    boolean isFoAppAlreadyAccepted(Integer efilingId);

    RudiApplicationDTO save(RudiApplicationDTO dto, ValidationScope validationScope);

    RudiApplicationDTO save(RudiApplicationDTO dto, ValidationScope validationScope, boolean isLoggable);

    List<RudiApplicationDTO> selectAppsWithSimilarDiplomasById(Integer applicationId, Integer diplomaYear, String countryName, String eduLevel, String originalEduLevel, String civilId, String ownerFirstName, String ownerLastName, LocalDate birthDate,String birthCountry, String diplomaOwnerEan);

}
