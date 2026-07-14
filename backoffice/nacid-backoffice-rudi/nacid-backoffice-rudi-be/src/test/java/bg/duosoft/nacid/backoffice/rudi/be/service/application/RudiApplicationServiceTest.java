package bg.duosoft.nacid.backoffice.rudi.be.service.application;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * User: ggeorgiev
 * Date: 06.01.2023
 * Time: 11:02
 */
public class RudiApplicationServiceTest extends RudiApplicationServiceTestBase {
    @Test
    public void testSaveApplication() {
        RudiApplicationDTO ras = createBaseDTO();
        ras = rudiApplicationService.save(ras, ValidationScope.NO_VALIDATION);
        ApplicationDTO apn = ras.getApplication();
        assertNotNull(apn);
        assertEquals("123", apn.getEntryNumber());
        assertEquals("admin", apn.getUserCreated());
        assertEquals("AR", apn.getApplicationType().getId());
        assertEquals("UDI", apn.getApplicationSubtype().getId());
        assertEquals("FILE", apn.getStatus().getId());
        assertEquals("POS", apn.getDocflowStatus().getId());
        assertEquals(LocalDate.of(2022, 1, 1), apn.getEntryDate());
        assertEquals("A", ras.getBgAddressOwner());
        assertEquals(true, apn.getDataAuthenticFlag());
        assertEquals(false, apn.getDiffDiplomaNamesFlag());
        assertEquals(true, apn.getPersonalDataUsageFlag());
        assertEquals(false, ras.getRepresentativeAuthorizedFlag());
    }
}
