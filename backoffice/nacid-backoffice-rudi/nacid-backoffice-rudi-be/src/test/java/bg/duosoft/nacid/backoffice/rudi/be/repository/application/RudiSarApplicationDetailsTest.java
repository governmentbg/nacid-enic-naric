package bg.duosoft.nacid.backoffice.rudi.be.repository.application;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User: ggeorgiev
 * Date: 23.01.2023
 * Time: 12:39
 */
public class RudiSarApplicationDetailsTest extends RudiApplicationEntityTestBase {
    @Test
    public void testSarApplicationDetails() {
        RudiApplicationEntity e = createBaseRudiApplicationEntity();
        SarApplicationEntity sarApplication = new SarApplicationEntity(null, e, 1, 0, 1, "on", "in", null, null, null);
        e.setSarApplication(sarApplication);
        e = rudiApplicationRepository.saveAndFlush(e);
        assertNotNull(e);
        sarApplication = e.getSarApplication();
        assertNotNull(sarApplication);

        assertEquals(1, sarApplication.getStatuteFlag());
        assertEquals(0, sarApplication.getAuthenticityFlag());
        assertEquals(1, sarApplication.getRecommendationFlag());
        assertEquals("on", sarApplication.getOutgoingNumber());
        assertEquals("in", sarApplication.getInternalNumber());

        e.setSarApplication(new SarApplicationEntity(e.getId(), e, 1, 1, 0, "on", "in", null, null, null));
        rudiApplicationRepository.saveAndFlush(e);

        //removing sar application (should be done in different test)
        e.setSarApplication(null);
        e = rudiApplicationRepository.saveAndFlush(e);
        assertNull(e.getSarApplication());

    }

}
