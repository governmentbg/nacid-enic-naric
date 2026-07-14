package bg.duosoft.nacid.backoffice.rudi.be.repository.report;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.StringIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.report.filter.application.ApplicationReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.report.filter.status.StatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.report.filter.status.StatusReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.report.filter.type.DocumentTypeReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ConnectionType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.JoinType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.RudiCommonReportFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.applicant.legal.LegalApplicantReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.applicant.natural_person.NaturalPersonApplicantReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.application.responsible_user.ApplicationResponsibleUserReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.application.type.ApplicationTypeReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.application.user_created.ApplicationUserCreatedReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.commission.CommissionReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.commission.CommissionStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.DiplomaReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.DiplomaSpecialityReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.RecognizedDiplomaSpecialityReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.diploma_owner.DiplomaOwnerSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.qualification.QualificationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.speciality.SpecialityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.document.receive_method.DocumentReceiveMethodReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.institution.TrainingInstitutionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.institution.TrainingInstitutionReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.representative.RepresentativeReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.university.UniversityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.university.UniversityReportSectionDTO;
import bg.duosoft.nacid.backoffice.rudi.be.TestBase;
import bg.duosoft.nacid.backoffice.rudi.be.repository.CommonReportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * User: ggeorgiev
 * Date: 30.08.2023
 * Time: 17:48
 */
public class CommonReportTest extends TestBase {
    @Autowired
    private CommonReportRepository repository;
    @Test
    public void testCommonReportCommissionNotReviewedFilterFilter() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        CommissionReportSectionDTO commission = new CommissionReportSectionDTO();
        commission.setIsNotCommissionReviewed(true);
        filter.setCommission(commission);
        repository.getReportApplications(filter);
    }
    @Test
    public void testCommonReportCommissionFilter() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        CommissionReportSectionDTO commission = new CommissionReportSectionDTO();
        CommissionStatusDTO status = new CommissionStatusDTO();
        status.setCommissionStatus("POS");
        commission.setCommissionStatuses(new ArrayList<>());
        commission.getCommissionStatuses().add(status);
        commission.setSessionNumberFrom(1);
        commission.setSessionNumberTo(500);
        commission.setIsCommissionReviewed(true);


        filter.setCommission(commission);

        repository.getReportApplications(filter);
    }
    @Test
    public void testCommonReportApplicationSarAllFilter() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        ApplicationTypeReportSectionDTO applicationFilter = new ApplicationTypeReportSectionDTO();
        applicationFilter.setApplicationTypes(Arrays.asList(new StringIdDTO("DOC"), new StringIdDTO("SAR")));
        applicationFilter.setSarServicesJoin(JoinType.ALL);
        applicationFilter.setSarServices(Stream.of("S", "A").map(StringIdDTO::new).toList());
        filter.setApplicationType(applicationFilter);
        repository.getReportApplications(filter);
    }
    @Test
    public void testCommonReportApplicationSarAnyFilter() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        ApplicationTypeReportSectionDTO applicationFilter = new ApplicationTypeReportSectionDTO();
        applicationFilter.setApplicationTypes(Arrays.asList(new StringIdDTO("DOC"), new StringIdDTO("SAR")));
        applicationFilter.setSarServicesJoin(JoinType.ANY);
        applicationFilter.setSarServices(Stream.of("S", "A").map(StringIdDTO::new).toList());
        filter.setApplicationType(applicationFilter);
        repository.getReportApplications(filter);
    }
    @Test
    public void testCommonReportApplicationSarOnlyAllFilter() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        ApplicationTypeReportSectionDTO applicationFilter = new ApplicationTypeReportSectionDTO();
        applicationFilter.setApplicationTypes(Arrays.asList(new StringIdDTO("DOC"), new StringIdDTO("SAR")));
        applicationFilter.setSarServicesJoin(JoinType.ONLY_ALL);
        applicationFilter.setSarServices(Stream.of("S", "A").map(StringIdDTO::new).toList());
        filter.setApplicationType(applicationFilter);
        repository.getReportApplications(filter);
    }

    @Test
    public void testCommonReportDiplomaFilter() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        filter.setDiploma(new DiplomaReportSectionDTO());
        filter.getDiploma().setIsStateApproved(true);
        filter.getDiploma().setDiplomaYearFrom(2000);
        filter.getDiploma().setDiplomaYearTo(2020);
        repository.getReportApplications(filter);
    }

    @Test
    public void testCommonReportStatusFilter() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        StatusReportSectionDTO statusSection = new StatusReportSectionDTO();
        statusSection.setStatuses(new ArrayList<>());
        StatusDTO status = new StatusDTO(false, "DEN", LocalDate.of(2000, 1, 1), LocalDate.of(2030, 1, 1), "POS", LocalDate.of(2000, 1, 1), LocalDate.of(2030, 1, 1), Stream.of(1, 2).map(IntegerIdDTO::new).toList(), "POS", LocalDate.of(2000, 1, 1), LocalDate.of(2020, 1, 1));
        statusSection.getStatuses().add(status);
        status = new StatusDTO(true, "DEN", LocalDate.of(2000, 1, 1), LocalDate.of(2030, 1, 1), null, null, null, null, null, null, null);
        statusSection.getStatuses().add(status);
        filter.setStatus(statusSection);
        repository.getReportApplications(filter);
    }
    @Test
    public void testApplicationFilter() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        ApplicationReportSectionDTO application = new ApplicationReportSectionDTO();
        application.setApplicationDateFrom(LocalDate.of(2022, 1, 1));
        application.setApplicationDateTo(LocalDate.of(2023, 1, 1));
        application.setEntryNumber("06-00-239");
        filter.setApplication(application);
        repository.getReportApplications(filter);
    }

    @Test
    public void testApplicationFilterBackofficeDate() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        ApplicationReportSectionDTO application = new ApplicationReportSectionDTO();
        application.setBackofficeDateFrom(LocalDate.of(2022, 1, 1));
        application.setBackofficeDateTo(LocalDate.of(2023, 1, 1));
        filter.setApplication(application);
        repository.getReportApplications(filter);
    }

    @Test
    public void testDiplomaOwnerFilter() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        DiplomaOwnerSectionDTO diplomaOwner = new DiplomaOwnerSectionDTO();
        diplomaOwner.setCountries(Stream.of("FR", "BG").map(StringIdDTO::new).toList());
        diplomaOwner.setIdentifier("8812306520");
        diplomaOwner.setFirstName("дим");
        diplomaOwner.setMiddleName("ник");
        diplomaOwner.setLastName("поп");
        filter.setDiplomaOwner(diplomaOwner);
        repository.getReportApplications(filter);
        System.out.println(repository.getReportApplicationsCount(filter));
    }

    @Test
    public void testDocumentTypeFilter() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
//        filter.setDiplomaOwnerCountry(new CountryReportSectionDTO());
//        filter.getDiplomaOwnerCountry().setCountries(Stream.of("FR", "DE").map(StringIdDTO::new).toList());
        filter.setDocumentType(new DocumentTypeReportSectionDTO());
        filter.getDocumentType().setDocumentTypes(Stream.of(1).map(IntegerIdDTO::new).toList());
        repository.getReportApplications(filter);
    }
    @Test
    public void testNaturalApplicantFilter() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        NaturalPersonApplicantReportSectionDTO a = new NaturalPersonApplicantReportSectionDTO();
        filter.setNaturalPersonApplicant(a);

        a.setPersonalDocumentTypes(Stream.of("ID", "RCT").map(StringIdDTO::new).toList());
        a.setFirstName("иван");
        filter.setPage(1);

        repository.getReportApplications(filter);
        System.out.println(repository.getReportApplicationsCount(filter));
    }
    @Test
    public void testRepresentativeFilter() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        RepresentativeReportSectionDTO repr = new RepresentativeReportSectionDTO();
        repr.setNaturalPerson(new NaturalPersonApplicantReportSectionDTO());
        repr.getNaturalPerson().setIdentifier("10101010");
//        repr.setCompanies();
        filter.setRepresentative(repr);
        throw new RuntimeException("Not implemented");
    }
    @Test
    public void testDocumentReceiveMethodFilter() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        filter.setDocumentReceiveMethod(new DocumentReceiveMethodReportSectionDTO());
        filter.getDocumentReceiveMethod().setDocumentReceiveMethods(Stream.of("DEC", "D").map(StringIdDTO::new).toList());
        repository.getReportApplications(filter);
    }

    @Test
    public void testUserCreatedFilter() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        filter.setApplicationUserCreated(new ApplicationUserCreatedReportSectionDTO());
        filter.getApplicationUserCreated().setUsers(Stream.of("nacid").map(StringIdDTO::new).toList());
        filter.setPage(2);
        System.out.println(repository.getReportApplicationsCount(filter));
        repository.getReportApplications(filter);
    }
    @Test
    public void testResponsibleUsersFilter() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        filter.setApplicationResponsibleUser(new ApplicationResponsibleUserReportSectionDTO());
        filter.getApplicationResponsibleUser().setUsers(Stream.of("boby").map(StringIdDTO::new).toList());
        repository.getReportApplications(filter);
    }
    @Test
    public void testOnlyActiveResponsibleUsersFilter() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        filter.setApplicationResponsibleUser(new ApplicationResponsibleUserReportSectionDTO());
        filter.getApplicationResponsibleUser().setUsers(Stream.of("boby").map(StringIdDTO::new).toList());
        filter.getApplicationResponsibleUser().setOnlyActiveResponsibleUsers(true);
        repository.getReportApplications(filter);
    }

    @Test
    public void testLegalApplicantFilter() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        filter.setLegalApplicant(new LegalApplicantReportSectionDTO());
        filter.getLegalApplicant().setLegalApplicants(Stream.of(82620, 82642).map(IntegerIdDTO::new).toList());
        filter.getLegalApplicant().setLegalApplicantNames(Stream.of("ценов").toList());
        repository.getReportApplications(filter);
    }
    @Test
    public void testTrainingInstitutionCriteria() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        filter.setTrainingInstitution(new TrainingInstitutionReportSectionDTO());
        filter.getTrainingInstitution().setTrainingInstitutions(new ArrayList<>());
        filter.getTrainingInstitution().getTrainingInstitutions().add(new TrainingInstitutionDTO(new StringIdDTO("BG"), Stream.of(77).map(IntegerIdDTO::new).toList(), Arrays.asList("университет")));
        filter.getTrainingInstitution().getTrainingInstitutions().add(new TrainingInstitutionDTO(new StringIdDTO("UA"), Stream.of(78).map(IntegerIdDTO::new).toList(), Arrays.asList("донецк")));
        repository.getReportApplications(filter);
    }

    @Test
    public void testRecognitionDetailsCriteria() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        RecognizedDiplomaSpecialityReportSectionDTO rs = new RecognizedDiplomaSpecialityReportSectionDTO();
        filter.setRecognizedDiplomaSpeciality(rs);
        rs.setEduLevels(Stream.of("BAC", "MAS").map(StringIdDTO::new).toList());
        rs.setQualifications(Stream.of("Компютърен инженер").map(StringIdDTO::new).toList());
        rs.setQualificationNames(Arrays.asList("строител"));
        rs.setSpecialities(Stream.of("История").map(StringIdDTO::new).toList());
        rs.setSpecialityNames(Arrays.asList("хидро"));
        repository.getReportApplications(filter);

    }

    @Test
    public void testSpecialitiesCriteria() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        DiplomaSpecialityReportSectionDTO ds = new DiplomaSpecialityReportSectionDTO();
        filter.setDiplomaSpeciality(ds);
        ds.setSpeciality(new SpecialityDTO());
        ds.setQualification(new QualificationDTO());
        ds.getQualification().setQualifications(Stream.of("Психолог").map(StringIdDTO::new).toList());
        ds.getQualification().setQualificationNames(Arrays.asList("administrator"));
        ds.getSpeciality().setSpecialities(Stream.of("История").map(StringIdDTO::new).toList());
        ds.getSpeciality().setSpecialityNames(Arrays.asList("хидро"));

        ds.getSpeciality().setOriginalSpecialities(Stream.of("Master of Science").map(StringIdDTO::new).toList());
        ds.getSpeciality().setOriginalSpecialityNames(Arrays.asList("bachelor"));
        System.out.println(repository.getReportApplicationsCount(filter));
        repository.getReportApplications(filter);

    }


    @Test
    public void testUniversitiesCriteria() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        UniversityReportSectionDTO unyCriteria = new UniversityReportSectionDTO();
        UniversityDTO universityDTO = new UniversityDTO(new StringIdDTO("BG"), Stream.of(1,2).map(IntegerIdDTO::new).toList(), Arrays.asList("тест"), Arrays.asList("entest"));
        unyCriteria.setUniversities(Arrays.asList(universityDTO));
        filter.setUniversity(unyCriteria);

        repository.getReportApplications(filter);

    }

    @Test
    public void testUniversitiesWithRegistersCriteria() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        UniversityReportSectionDTO unyCriteria = new UniversityReportSectionDTO();
        unyCriteria.setOnlyWithDiplomaRegisters(true);
        filter.setUniversity(unyCriteria);
        repository.getReportApplications(filter);
        System.out.println(repository.getReportApplicationsCount(filter));
    }
    @Test
    public void testUniversitiesJointUniversitiesCriteria() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        UniversityReportSectionDTO unyCriteria = new UniversityReportSectionDTO();
        unyCriteria.setOnlyJointDegree(true);
        filter.setUniversity(unyCriteria);
        repository.getReportApplications(filter);
        System.out.println(repository.getReportApplicationsCount(filter));
    }

    @Test
    public void testUniversitiesNoJointUniversitiesCriteria() {
        RudiCommonReportFilterDTO filter = new RudiCommonReportFilterDTO();
        UniversityReportSectionDTO unyCriteria = new UniversityReportSectionDTO();
        unyCriteria.setOnlyJointDegree(false);
        filter.setUniversity(unyCriteria);
        repository.getReportApplications(filter);
        System.out.println(repository.getReportApplicationsCount(filter));
    }
}
