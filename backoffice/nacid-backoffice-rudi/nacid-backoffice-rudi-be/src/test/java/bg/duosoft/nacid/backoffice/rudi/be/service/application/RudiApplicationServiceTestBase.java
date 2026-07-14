package bg.duosoft.nacid.backoffice.rudi.be.service.application;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationSubtypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.TestBase;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * User: ggeorgiev
 * Date: 06.01.2023
 * Time: 10:46
 */
public class RudiApplicationServiceTestBase extends TestBase {
    @Autowired
    protected RudiApplicationService rudiApplicationService;
    protected RudiApplicationDTO createBaseDTO() {
        RudiApplicationDTO res = new RudiApplicationDTO();
        res.setBgAddressOwner("A");
        ApplicationDTO apn = new ApplicationDTO();
        apn.setRowVersion(1);
        apn.setDateCreated(LocalDateTime.now());
        apn.setEntryDate(LocalDate.of(2022, 1, 1));
        apn.setApplicationType(new ApplicationTypeDTO("AR"));
        apn.setApplicationSubtype(new ApplicationSubtypeDTO("UDI"));
        apn.setStatus(new ReferenceDataDTO(ReferenceDataDomain.APPLICATION_STATUS.domain(), "FILE"));
        apn.setEntryNumber("123");
        apn.setDocflowStatus(new ReferenceDataDTO(ReferenceDataDomain.DOCFLOW_STATUS.domain(), "POS"));
        apn.setDataAuthenticFlag(true);
        apn.setDiffDiplomaNamesFlag(false);
        apn.setPersonalDataUsageFlag(true);
        apn.setUserCreated("admin");
//        res.setRepresentativeAuthorizedFlag(false);
        res.setApplication(apn);

        return res;
    }

}
