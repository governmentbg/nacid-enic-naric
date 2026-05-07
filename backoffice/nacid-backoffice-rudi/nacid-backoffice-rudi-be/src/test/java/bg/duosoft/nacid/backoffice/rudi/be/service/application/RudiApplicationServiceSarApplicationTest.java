package bg.duosoft.nacid.backoffice.rudi.be.service.application;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.SarApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * User: ggeorgiev
 * Date: 25.01.2023
 * Time: 11:57
 */
public class RudiApplicationServiceSarApplicationTest extends RudiApplicationServiceTest {
    @Test
    public void testSarApplication() {
        RudiApplicationDTO ras = createBaseDTO();
        ras.setSarApplication(new SarApplicationDTO(true, false, true, "on", "in", null, null, null));
        ras = rudiApplicationService.save(ras, ValidationScope.NO_VALIDATION);
        assertNotNull(ras);
        assertNotNull(ras.getSarApplication());
        assertEquals(true, ras.getSarApplication().getIsStatute());
        assertEquals(false, ras.getSarApplication().getIsAuthenticity());
        assertEquals(true, ras.getSarApplication().getIsRecommendation());
        assertEquals("on", ras.getSarApplication().getOutgoingNumber());
        assertEquals("in", ras.getSarApplication().getInternalNumber());

    }
}
