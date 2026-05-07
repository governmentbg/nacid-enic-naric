package bg.duosoft.nacid.backoffice.rudi.be.repository.application;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CommissionMemberPositionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationCommissionMemberEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationCommissionMemberSpecialityEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CommissionMemberEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.RudiApplicationEntity;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * User: ggeorgiev
 * Date: 05.01.2023
 * Time: 18:08
 */
public class RudiApplicationEntityCommissionMemberTest extends RudiApplicationEntityTestBase {
    @Test
    public void testApplicationCommissionMembers() {
        RudiApplicationEntity ran = createBaseRudiApplicationEntity();
        ApplicationCommissionMemberEntity e = new ApplicationCommissionMemberEntity();
        e.setApplication(ran);
        e.setNotes("test");
        e.setCourseContent("алабала");
        e.setEduLevel(new ReferenceDataEntity(ReferenceDataDomain.EDUCATION_LEVEL, "MAS"));
        e.setPreviousBoardDecisions("test2");
        e.setProcessStatus(1);
        e.setQualification("test3");
        e.setSimilarBulgarianPrograms("test5");
        CommissionMemberEntity cm = new CommissionMemberEntity(1);
        e.setCommissionMember(cm);
        e.setCommissionMemberPosition(new CommissionMemberPositionEntity("ACK"));
        e.setApplicationCommissionMemberSpecialities(new ArrayList<>());
        e.getApplicationCommissionMemberSpecialities().add(new ApplicationCommissionMemberSpecialityEntity(null, e, "test speciality"));
        ran.setApplicationCommissionMembers(new ArrayList<>());
        ran.getApplicationCommissionMembers().add(e);
        ran = rudiApplicationRepository.saveAndFlush(ran);
        assertNotNull(ran);
        assertEquals(1, ran.getApplicationCommissionMembers().size());
        e = ran.getApplicationCommissionMembers().get(0);
        List<ApplicationCommissionMemberSpecialityEntity> specialities = e.getApplicationCommissionMemberSpecialities();
        assertEquals(1, specialities.size());
        assertEquals("test", e.getNotes());
        assertEquals("алабала", e.getCourseContent());
        assertEquals("test2", e.getPreviousBoardDecisions());
        assertEquals(1, e.getProcessStatus());
        assertEquals("test3", e.getQualification());
        assertEquals("test5", e.getSimilarBulgarianPrograms());
        assertNotNull(e.getCommissionMemberPosition());
        assertEquals("ACK", e.getCommissionMemberPosition().getId());
        assertEquals("test speciality", specialities.get(0).getSpeciality());

    }
}
