package bg.duosoft.nacid.backoffice.rudi.be.repository.application;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ApplicationSubtypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ApplicationTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntityPK;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.rudi.be.TestBase;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.RudiApplicationEntity;
import bg.duosoft.nacid.backoffice.rudi.be.repository.RudiApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

abstract class RudiApplicationEntityTestBase extends TestBase {

    @Autowired
    protected RudiApplicationRepository rudiApplicationRepository;

    protected RudiApplicationEntity createBaseRudiApplicationEntity() {
        RudiApplicationEntity ran = new RudiApplicationEntity();

//        Application apn = new Application();
        ran.setRowVersion(1);
        ran.setApplicationType(new ApplicationTypeEntity("AR", null, null));
        ran.setApplicationSubtype(new ApplicationSubtypeEntity("UDI", null, null, ran.getApplicationType()));
        ran.setDateCreated(LocalDateTime.now());
        ran.setUserCreated("test");
        ran.setEntryDate(LocalDate.now());
        ran.setEntryNumber("123");
        ran.setStatus(new ReferenceDataEntity());
        ran.getStatus().setPk(new ReferenceDataEntityPK(ReferenceDataDomain.APPLICATION_STATUS.domain(), "FILE"));


        ran.setDocflowStatus(new ReferenceDataEntity());
        ran.getDocflowStatus().setPk(new ReferenceDataEntityPK(ReferenceDataDomain.DOCFLOW_STATUS.domain(), "POS"));

        ran.setBgAddressOwner("A");
        ran.setDataAuthenticFlag(1);
        ran.setRepresentativeAuthorizedFlag(1);
        ran.setDiffDiplomaNamesFlag(0);
        ran.setPersonalDataUsageFlag(0);
        ran.setOfficialEmailCommunicationFlag(1);

        return ran;
    }

}
