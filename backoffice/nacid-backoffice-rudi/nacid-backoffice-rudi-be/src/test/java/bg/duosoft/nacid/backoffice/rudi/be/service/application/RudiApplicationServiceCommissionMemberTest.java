package bg.duosoft.nacid.backoffice.rudi.be.service.application;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CommissionMemberPositionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LegalReasonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationCommissionMemberDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationCommissionMemberSpecialityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionMemberDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * User: ggeorgiev
 * Date: 06.01.2023
 * Time: 11:03
 */
public class RudiApplicationServiceCommissionMemberTest extends RudiApplicationServiceTestBase {
    @Test
    public void testSaveApplicationCommissionMember() {
        RudiApplicationDTO dto = createBaseDTO();
        List<ApplicationCommissionMemberDTO> acms = new ArrayList<>();
        ApplicationCommissionMemberDTO acm = new ApplicationCommissionMemberDTO();
        acm.setNotes("test notes");
        acm.setCommissionMember(new CommissionMemberDTO(1));
        acm.setCourseContent("course content");
        acm.setPreviousBoardDecisions("previous board decisions");
        acm.setProcessStatus(true);
        acm.setQualification("test qualification");
        acm.setSimilarBulgarianPrograms("similar bulgarian programs");
        acm.setEduLevel(new ReferenceDataDTO(ReferenceDataDomain.EDUCATION_LEVEL.domain(), "MAS"));
        acm.setCommissionMemberPosition(new CommissionMemberPositionDTO("ACK"));
        acm.setLegalReason(new LegalReasonDTO(1));
        acms.add(acm);
        acm.setApplicationCommissionMemberSpecialities(new ArrayList<>());

        ApplicationCommissionMemberSpecialityDTO acmspec = new ApplicationCommissionMemberSpecialityDTO();
        acmspec.setSpeciality("test speciality");
        acm.getApplicationCommissionMemberSpecialities().add(acmspec);
        dto.setApplicationCommissionMembers(acms);
        dto = rudiApplicationService.save(dto, ValidationScope.NO_VALIDATION);


        assertNotNull(dto.getApplicationCommissionMembers());
        assertEquals(1, dto.getApplicationCommissionMembers().size());
        acm = dto.getApplicationCommissionMembers().get(0);
        assertNotNull(acm);
        assertEquals("test notes", acm.getNotes());
        assertEquals("course content", acm.getCourseContent());
        assertEquals("previous board decisions", acm.getPreviousBoardDecisions());
        assertEquals("test qualification", acm.getQualification());
        assertEquals("similar bulgarian programs", acm.getSimilarBulgarianPrograms());
        assertEquals(1, acm.getProcessStatus());
        assertNotNull(acm.getEduLevel());
        assertEquals("MAS", acm.getEduLevel().getId());
        assertNotNull(acm.getCommissionMemberPosition());
        assertEquals("ACK", acm.getCommissionMemberPosition().getId());
        assertNotNull(acm.getLegalReason());
        assertEquals(1, acm.getLegalReason().getId());
        assertNotNull(acm.getApplicationCommissionMemberSpecialities());
        assertEquals(1, acm.getApplicationCommissionMemberSpecialities().size());
        acmspec = acm.getApplicationCommissionMemberSpecialities().get(0);
        assertEquals("test speciality", acmspec.getSpeciality());

    }
}
