package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.forms.application.common.education;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.GradingScaleDTO;

import java.util.List;

public interface SeMandatoryEduData {
    CountryDTO getSchoolCountry();

    String getSchoolSettlement();

    String getSchoolName();

    String getDiplomaNumber();

    Integer getDiplomaYear();

    GradingScaleDTO getGradingScale();

    List<String> getRecognitionPurposes();

}
