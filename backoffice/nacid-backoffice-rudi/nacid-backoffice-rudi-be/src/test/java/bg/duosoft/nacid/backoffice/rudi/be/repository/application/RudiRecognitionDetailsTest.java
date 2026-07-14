package bg.duosoft.nacid.backoffice.rudi.be.repository.application;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ProfGroupEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationRecognitionPurposeEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationRecognizedDetailsEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationRecognizedSpecialityEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.RudiApplicationEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User: ggeorgiev
 * Date: 23.01.2023
 * Time: 12:39
 */
public class RudiRecognitionDetailsTest  extends RudiApplicationEntityTestBase {
    @Test
    public void testRecognitionDetails() {
        RudiApplicationEntity e = createBaseRudiApplicationEntity();
        ApplicationRecognizedDetailsEntity rd = new ApplicationRecognizedDetailsEntity();
        rd.setRecognizedEduLevel("MAS");
        rd.setRecognizedQualification("test");
        rd.setProfGroup(new ProfGroupEntity(1101));
        e.setApplicationRecognizedDetails(rd);
        rd.setApplication(e);
        e = rudiApplicationRepository.saveAndFlush(e);
        assertNotNull(e);
        assertNotNull(e.getApplicationRecognizedDetails());
        assertEquals("MAS", e.getApplicationRecognizedDetails().getRecognizedEduLevel());
        assertEquals("test", e.getApplicationRecognizedDetails().getRecognizedQualification());
        assertNotNull(e.getApplicationRecognizedDetails().getProfGroup());
        assertEquals(1101, e.getApplicationRecognizedDetails().getProfGroup().getId());
    }
    @Test
    public void testRecognizedSpecialities() {
        RudiApplicationEntity e = createBaseRudiApplicationEntity();
        e.setRecognizedSpecialities(new ArrayList<>());
        e.getRecognizedSpecialities().add(new ApplicationRecognizedSpecialityEntity(null, e,  "test"));
        e.getRecognizedSpecialities().add(new ApplicationRecognizedSpecialityEntity(null, e, "test2"));
        e = rudiApplicationRepository.saveAndFlush(e);
        assertNotNull(e);
        assertNotNull(e.getRecognizedSpecialities());
        assertEquals(2, e.getRecognizedSpecialities().size());
        assertEquals("test", e.getRecognizedSpecialities().get(0).getSpeciality());
        assertEquals("test2", e.getRecognizedSpecialities().get(1).getSpeciality());
    }
    @Test
    public void testRecognitionPurposes() {
        RudiApplicationEntity e = createBaseRudiApplicationEntity();
        List<ApplicationRecognitionPurposeEntity> rps = new ArrayList<>();
        rps.add(new ApplicationRecognitionPurposeEntity(null, e, new ReferenceDataEntity(ReferenceDataDomain.RECOGNITION_PURPOSE, "OTH"), "TEST"));
        rps.add(new ApplicationRecognitionPurposeEntity(null, e, new ReferenceDataEntity(ReferenceDataDomain.RECOGNITION_PURPOSE, "S"), null));
        e.setApplicationRecognitionPurposes(rps);
        e = rudiApplicationRepository.saveAndFlush(e);
        assertNotNull(e);
        assertNotNull(e.getApplicationRecognitionPurposes());
        assertEquals(2, e.getApplicationRecognitionPurposes().size());
        assertEquals("OTH", e.getApplicationRecognitionPurposes().get(0).getRecognitionPurpose().getPk().getId());
        assertEquals("TEST", e.getApplicationRecognitionPurposes().get(0).getNotes());
        assertEquals("S", e.getApplicationRecognitionPurposes().get(1).getRecognitionPurpose().getPk().getId());
        assertNull(e.getApplicationRecognitionPurposes().get(1).getNotes());

    }
}
