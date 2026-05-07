package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.abdocs.domain.DocFile;
import bg.duosoft.nacid.backoffice.abdocs.domain.DocFileVisibility;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.DownloadFileResponse;
import bg.duosoft.nacid.backoffice.abdocs.service.main.AbdocsAdminService;
import bg.duosoft.nacid.backoffice.abdocs.util.AbdocsUrlBuilder;
import bg.duosoft.nacid.backoffice.core.client.client.common.applicantattacheddocs.AdminApplicationAttachedDocsClient;
import bg.duosoft.nacid.backoffice.core.client.client.fileStore.BoAdminFileStoreClient;
import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.externalnomenclaturesmap.AdminExternalNomenclaturesMapClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.file.FileStoreEntryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.*;
import bg.duosoft.nacid.backoffice.core.data.util.common.CommonUtils;
import bg.duosoft.nacid.backoffice.rudi.be.repository.RudiApplicationRepository;
import bg.duosoft.nacid.backoffice.rudi.be.service.RasService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.validator.RegisterRasApplicationValidator;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import bg.duosoft.nacid.ras.client.RasClient;
import bg.duosoft.nacid.ras.dto.*;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import bg.duosoft.nacidshareddata.util.date.DateUtils;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ExternalNomenclatureSystem.RAS;
import static bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ExternalNomenclatureType.*;

/**
 * User: ggeorgiev
 * Date: 09.06.2023
 * Time: 10:12
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RasServiceImpl implements RasService {
    private final RudiApplicationService rudiApplicationService;
    private final RudiApplicationRepository applicationRepository;
    private final RasClient rasClient;
    private final BoAdminFileStoreClient fileStoreClient;
    private final AdminExternalNomenclaturesMapClient externalNomenclaturesMapClient;
    private final AbdocsUrlBuilder abdocsUrlBuilder;
    private final RegisterRasApplicationValidator registerRasApplicationValidator;
    private final AdminApplicationAttachedDocsClient applicationAttachedDocsClient;
    private final AbdocsAdminService abdocsAdminService;

    public boolean isApplicationTransferredInRas(Integer applicationId) {
        return !ObjectUtils.isEmpty(applicationRepository.getExternalSystemIdById(applicationId));
    }

    @Override
    public synchronized void registerRasApplication(Integer applicationId, Integer certificateFileId) {
        //TODO certificateFileId = DocFile.id -> selectCertificateFiles

        RudiApplicationDTO rudiApplication = rudiApplicationService.selectById(applicationId);
        TrainingCourseDTO tc = rudiApplication.getTrainingCourse();
        ApplicationDTO baseApplication = rudiApplication.getApplication();

        /**
         * tyj kato ne iskam da call-vam service-a za chetene na cetificate file-a ot abdocs 2 pyti - vednuj vyv validatora i vednyj
         * v service-a, izmislih slednoto ne losho reshenie - podavam edin dopylnitelen argument kym validatora, v koito shte mi se vyrne
         * certificate content-a sled kato validator-a go prochete....
         */
        RegisterRasApplicationValidator.CertFileHolder certFileHolder = new RegisterRasApplicationValidator.CertFileHolder(certificateFileId);
        BadRequestValidator.validateRequest(registerRasApplicationValidator, rudiApplication, this, certFileHolder);

        ApplicationRecognizedDetailsDTO recognitionDetails = rudiApplication.getApplicationRecognizedDetails();
        List<ApplicationCertificatesDTO> certs = baseApplication.getCertificatesByCertificateStatus(ReferenceDataCode.CERTIFICATE_STATUS_PUBLISHED.code());
        ApplicationCertificatesDTO cert = ObjectUtils.isEmpty(certs) ? null : certs.get(0);

        AddressDTO contactAddress = baseApplication.getContactAddress();

//        TrainingCourseTrainingLocation baseTrainingLocation = !CollectionUtils.isEmpty(tc.getTrainingCourseTrainingLocations()) ? tc.getTrainingCourseTrainingLocations().get(0) : null;
//        TrainingInstitution trainingInstitution = baseTrainingLocation == null ? null : baseTrainingLocation.getTrainingInstitution();

        RegisterApplicationRequest rq = new RegisterApplicationRequest();
        Applicant applicant = new Applicant();

        applicant.setContactPhone(contactAddress == null ? null : contactAddress.getPhone());
        applicant.setCorrespondentType(1);
        applicant.setFirstName("RUDi");
        applicant.setLastName("Admin");
        applicant.setUin("0000000000");
        applicant.setCorrespondentContacts(new ArrayList<>());
        CorrespondentContact cc = new CorrespondentContact();
        cc.setEmail(contactAddress == null ? null : contactAddress.getEmail());
        applicant.getCorrespondentContacts().add(cc);

        rq.setApplicant(applicant);
        rq.setElectronicServiceUri(externalNomenclaturesMapClient.getExternalNomIdBySystemNomenclatureTypeInternalNomId(RAS.code(), RAS_RUDI_APPLICATION_TYPE.code(), baseApplication.getApplicationSubtype().getId()));//код на електронната услуга за научна степен

        AcademicDegree academicDegree = new AcademicDegree();
        academicDegree.setAcademicDegreeTypeId(externalNomenclaturesMapClient.getExternalNomIdAsIntegerBySystemNomenclatureTypeInternalNomId(RAS.code(), RAS_EDU_LEVEL.code(), recognitionDetails.getRecognizedEduLevel()));


        //Ot primerite, koito gledah, te slagat diplomaDate/diplomaNumber na udostoverenieto v NACID!Zatova i az taka go pravq, no da se obsydi s tqh!!!!
//            academicDegree.setDiplomaDate(rudiApplication.getTrainingCourse().getDiplomaDate());
//            academicDegree.setDiplomaNumber(rudiApplication.getTrainingCourse().getDiplomaNumber());


        String fullCertNumber = cert.getCertificateNumber();
        String[] parts = fullCertNumber.split("/");
        String certNumber = parts[0];
        LocalDate certDate = DateUtils.parseDate(parts[1]);
        academicDegree.setCertificateNumber(certNumber);
        academicDegree.setCertificateDate(certDate);
        //Tozi kod e prehvyrlen po sy6tiq nachin ot staroto prilojenie. Vyrti mi se neshto v glavata che narochno i na dvete mesta (diplomaNumber/certificateNumber) se podava certificateNumber-a, poluchen ot NACID, zatova go ostavqm taka!!!
        academicDegree.setDiplomaNumber(academicDegree.getCertificateNumber());
        academicDegree.setDiplomaDate(academicDegree.getCertificateDate());


        academicDegree.setActive(true);
        academicDegree.setInstitutionId(externalNomenclaturesMapClient.getExternalNomIdAsIntegerBySystemNomenclatureTypeInternalNomId(RAS.code(), RAS_INSTITUTION.code(), "NACID"));
//            academicDegree.setIndicatorsSum(null);
//            academicDegree.setTotalSumChecked(null);
        Integer rasResearchArea = recognitionDetails == null || recognitionDetails.getProfGroup() == null ? null : externalNomenclaturesMapClient.getExternalNomIdAsIntegerBySystemNomenclatureTypeInternalNomId(RAS.code(), ExternalNomenclatureType.RAS_PROF_GROUP.code(), recognitionDetails.getProfGroup().getId().toString());
        academicDegree.setResearchAreaId(rasResearchArea);
        academicDegree.setGraduatedAbroad(true);

        TrainingLocationDTO tl = ObjectUtils.isEmpty(tc.getTrainingLocations()) ? null : tc.getTrainingLocations().get(0);
        //countryId-to se vzema ot baseTrainingLocation. Ako nqma base trainingLocation - ot university-to
        CountryDTO country = tl == null ? null : tc.getTrainingLocations().get(0).getCountry();
        if (country == null) {
            country = tc.getBaseUniversity() == null ? null : tc.getBaseUniversity().getCountry();
        }
        academicDegree.setCountryId(country == null ? null : externalNomenclaturesMapClient.getExternalNomIdAsIntegerBySystemNomenclatureTypeInternalNomId(RAS.code(), RAS_COUNTRY.code(), country.getId()));


        //za city-to - pyrvo se proverqba dali ima vyveden trainingLocation-> city. Ako nqma, se vzema trainingInstitution's city. Ako i tam nqma, se vzema ot baseUni-to
        String nacidForeignTown = tl != null && !ObjectUtils.isEmpty(tl.getCity()) ? tl.getCity() : (tl != null && tl.getExaminationTrainingInstitution() != null && !ObjectUtils.isEmpty(getAddressCity(tl.getExaminationTrainingInstitution().getAddress())) ? getAddressCity(tl.getExaminationTrainingInstitution().getAddress()) : (tc.getBaseUniversity() != null && !ObjectUtils.isEmpty(getAddressCity(tc.getBaseUniversity().getAddress())) ? getAddressCity(tc.getBaseUniversity().getAddress()) : null));
        academicDegree.setForeignTown(nacidForeignTown);

        if (tc.getBaseUniversity() != null) {
            academicDegree.setForeignInstitution(tc.getBaseUniversity().getBgName());
            academicDegree.setForeignInstitutionAlt(tc.getBaseUniversity().getOrgName());
        }

        Dissertation dissertation = new Dissertation();
        academicDegree.setDissertation(dissertation);


        dissertation.setAnnotation(tc.getThesisAnnotation());
        dissertation.setAnnotationAlt(tc.getThesisAnnotationEn());
        dissertation.setDateOfAcquire(tc.getThesisDefenceDate());
        dissertation.setDissertationIsNotDeposited(true);//Дисертацията не подлежи на депозиране в НАЦИД//TODO:Spored men trqbva da e true!!!!
        dissertation.setLanguageId(tc.getThesisLanguage() == null ? null : externalNomenclaturesMapClient.getExternalNomIdAsIntegerBySystemNomenclatureTypeInternalNomId(RAS.code(), RAS_LANGUAGE.code(), tc.getThesisLanguage().getId()));
        dissertation.setNumberOfBibliography(tc.getThesisBibliography());
        dissertation.setNumberOfPages(tc.getThesisVolume());
        dissertation.setTitle(tc.getThesisTopic());
        dissertation.setTitleAlt(tc.getThesisTopicEn());
        dissertation.setSupervisor(tc.getScientificSupervisor());
        dissertation.setSupervisorAlt(tc.getScientificSupervisorEn());
        dissertation.setReviewers(tc.getReviewers());
        dissertation.setReviewersAlt(tc.getReviewersEn());
        dissertation.setHeadOfJury(tc.getJuryChair());
        dissertation.setHeadOfJuryAlt(tc.getJuryChairEn());
        dissertation.setJury(tc.getJuryMembers());
        dissertation.setJuryAlt(tc.getJuryMembersEn());


        StructuredData structuredData = new StructuredData();
        rq.setStructuredData(structuredData);
        structuredData.setAcademicDegree(academicDegree);


        Person person = new Person();

        PersonDTO owner = tc.getDiplomaOwner();
        person.setBirthDate(owner.getBirthDate());
        person.setFirstName(owner.getFirstName());
        person.setMiddleName(owner.getMiddleName());
        person.setLastName(owner.getLastName());

        person.setFirstNameAlt(owner.getLatinFirstName());//nqmame imena na anglijski
        person.setMiddleNameAlt(owner.getLatinMiddleName());
        person.setLastNameAlt(owner.getLatinLastName());

        if (Objects.equals(owner.getCitizenship().getId(), DefaultValue.BG_COUNTRY_CODE)) {
            person.setType(1);
        } else {
            person.setType(2);
            person.setCitizenshipCode(owner.getCitizenship().getId());
        }
        person.setBirthPlaceCode(owner.getOriginCountry() == null ? null : owner.getOriginCountry().getId());


        String uin = owner.getCivilId();
        person.setUin(uin);
        structuredData.setPerson(person);


        FileStorageResponse uploadCertResponse = uploadCertFile(certFileHolder.getCertFile());
        structuredData.setCertificateFile(uploadCertResponse);

        List<AttachedDocDTO> dissertationDocs = baseApplication.getAttachmentsByDocTypes(DocTypes.DISSERTATION_WORK.code());
        if (!CollectionUtils.isEmpty(dissertationDocs)) {
            FileStorageResponse rasDissertationFile = uploadFile(dissertationDocs.get(0));
            structuredData.setDissertationFile(rasDissertationFile);
        }
        List<AttachedDocDTO> abstractDocs = baseApplication.getAttachmentsByDocTypes(DocTypes.ABSTRACT.code());
        if (!CollectionUtils.isEmpty(abstractDocs)) {
            FileStorageResponse rasAbstractFile = uploadFile(abstractDocs.get(0));
            structuredData.setSummaryFile(rasAbstractFile);
        }

        List<AttachedDocDTO> diplomaScienceDegreeDocs = baseApplication.getAttachmentsByDocTypes(DocTypes.DIPLOMA_SCIENCE_DEGREE.code());
        if (!CollectionUtils.isEmpty(diplomaScienceDegreeDocs)) {
            FileStorageResponse rasFile = uploadFile(diplomaScienceDegreeDocs.get(0));
            structuredData.setDiplomaFile(rasFile);
        }

        RegisterApplicationResponse rs = rasClient.registerApplication(rq);
        baseApplication.setExternalSystemId(rs.getCode());
        baseApplication.setExternalSystemDate(LocalDateTime.now());
        rudiApplicationService.save(rudiApplication, ValidationScope.NO_VALIDATION);//TODO:Ako ima problem pri save-a zadyljitelno da se pravi opit za save v error log tablicata!!!!!!
    }

    @Override
    public DocrecRasInfoDTO selectRasApplicationInfo(Integer applicationId) {
        RudiApplicationDTO rudiApplication = rudiApplicationService.selectById(applicationId);
        if (Objects.isNull(rudiApplication)) {
            throw new RuntimeException("RUDI application with ID = " + applicationId + " doesn't exist !");
        }

        ApplicationSubType type = ApplicationSubType.selectByTypeAndSubType(rudiApplication.getApplication().getApplicationType().getId(), rudiApplication.getApplication().getApplicationSubtype().getId());
        if (type != ApplicationSubType.RUDI_DOC_DEGREE_RECOGNITION) {
            throw new RuntimeException("RAS is available only for docrec applications ! App ID: " + applicationId);
        }

        String externalSystemId = rudiApplication.getApplication().getExternalSystemId();

        DocrecRasInfoDTO rasInfo = new DocrecRasInfoDTO();
        rasInfo.setApplicationId(applicationId);
        rasInfo.setExternalSystemId(externalSystemId);
        rasInfo.setDoesMeetTransferRequirements(doesMeetTransferRequirements(rudiApplication));
        rasInfo.setExternalLink(abdocsUrlBuilder.viewRasApplication(externalSystemId));
        return rasInfo;
    }

    @Override
    public List<DocFile> selectCertificateFiles(Integer applicationId) {
        RudiApplicationDTO rudiApplication = rudiApplicationService.selectById(applicationId);
        if (Objects.isNull(rudiApplication)) {
            return null;
        }

        ApplicationCertificatesDTO certificate = rudiApplication.getApplication()
                .getCertificates()
                .stream()
                .filter(c -> Objects.equals(c.getCertificateStatus(), ReferenceDataCode.CERTIFICATE_STATUS_PUBLISHED.code()))
                .findFirst()
                .orElse(null);
        if (Objects.isNull(certificate)) {
            return null;
        }

        Integer certAttachedDocId = certificate.getApplicationAttachedDocId();
        if (Objects.isNull(certAttachedDocId)) {
            return null;
        }

        AttachedDocDTO attachedDocDTO = applicationAttachedDocsClient.selectById(certAttachedDocId);
        if (Objects.isNull(attachedDocDTO)) {
            return null;
        }

        Doc abdocsCertDocument = abdocsAdminService.getDocumentById(Integer.valueOf(attachedDocDTO.getDocflowId()));
        if (Objects.isNull(abdocsCertDocument)) {
            return null;
        }

        List<DocFile> docFiles = abdocsCertDocument.getDocFiles();
        if (CollectionUtils.isEmpty(docFiles)) {
            return null;
        }

        List<DocFile> publicFiles = docFiles.stream().filter(f -> f.getDocFileVisibility() == DocFileVisibility.PublicAttachedFile).toList();
        if (CollectionUtils.isEmpty(publicFiles)) {
            return null;
        }

        return publicFiles;
    }

    private boolean doesMeetTransferRequirements(RudiApplicationDTO docrecApplication) {
        String externalSystemId = docrecApplication.getApplication().getExternalSystemId();
        if (StringUtils.isNotEmpty(externalSystemId)) {
            return true;
        }

        String statusId = CommonUtils.selectId(docrecApplication.getApplication().getStatus());
        if (Objects.isNull(statusId) || !statusId.equals(ApplicationStatusType.ACKNOWLEDGED.code())) {
            return false;
        }

        String docflowStatusId = CommonUtils.selectId(docrecApplication.getApplication().getDocflowStatus());
        if (Objects.isNull(docflowStatusId) || !docflowStatusId.equals(DocflowStatusType.ISSUED.code())) {
            return false;
        }

        List<AttachedDocDTO> attachments = docrecApplication.getApplication().getAttachments();
        if (CollectionUtils.isEmpty(attachments)) {
            return false;
        }

        ApplicationType ate = ApplicationType.selectByCode(docrecApplication.getApplication().getApplicationType().getId());
        ApplicationSubType ase = ApplicationSubType.selectByTypeAndSubType(ate.code(), docrecApplication.getApplication().getApplicationSubtype().getId());
        Optional<CertificateDocTypes> cdt = CertificateDocTypes.getCertificateDocTypes(ate, ase);
        boolean hasCertificate = cdt.isPresent() && attachments.stream().anyMatch(a -> cdt.get().isAnyDocType(a.getDocumentType().getId()));
        return hasCertificate;
    }

    private String getAddressCity(AddressDTO a) {
        return a == null ? null : (a.getSettlement() == null ? a.getCity() : a.getSettlement().getName());
    }

    private FileStorageResponse uploadFile(AttachedDocDTO attachedDoc) {
        if (attachedDoc == null || ObjectUtils.isEmpty(attachedDoc.getAttachedDocAttachments())) {
            return null;
        }

        //sortiram gi v obraten red i vzemam tozi s naj-golqmo ID
        AttachmentDTO attachment = attachedDoc.getAttachedDocAttachments().stream().sorted(Comparator.comparing(AttachedDocAttachmentDTO::getId).reversed()).findFirst().map(r -> r.getAttachment()).orElseThrow();


        String fileName = attachment.getFileName();
        fileName = fileName.replaceAll("\"", "\\\\\"");
        String contentType = attachment.getContentType();
        String fileLocation = StringUtils.substringBeforeLast(attachment.getFileLocation(), "/");
        String fileId = StringUtils.substringAfterLast(attachment.getFileLocation(), "/");
        FileStoreEntryDTO fileContent = fileStoreClient.getFileDetailsAndContent(attachment.getBucketName(), fileLocation, fileId);
        MultipartFile file = new CustomMultipartFile(fileName, fileName, contentType, fileContent.getContent());
        return rasClient.filesStorage(file);
    }

    private FileStorageResponse uploadCertFile(DownloadFileResponse certFile) {
        MultipartFile file = new CustomMultipartFile(certFile.getFileName(), certFile.getFileName(), certFile.getType(), certFile.getContent());
        return rasClient.filesStorage(file);
    }

    private static class CustomMultipartFile implements MultipartFile {
        private String name;
        private String originalName;
        private String contentType;
        private byte[] content;

        public CustomMultipartFile(String name, String originalName, String contentType, byte[] content) {
            this.name = name;
            this.originalName = originalName;
            this.contentType = contentType;
            this.content = content;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getOriginalFilename() {
            return originalName;
        }

        @Override
        public String getContentType() {
            return contentType;
        }

        @Override
        public boolean isEmpty() {
            return content == null || content.length == 0;
        }

        @Override
        public long getSize() {
            return content == null ? 0 : content.length;
        }

        @Override
        public byte[] getBytes() {
            return content;
        }

        @Override
        public InputStream getInputStream() {
            return new ByteArrayInputStream(content);
        }

        @Override
        public void transferTo(File dest) throws IOException, IllegalStateException {
            try (FileOutputStream fos = new FileOutputStream(dest)) {
                fos.write(content);
            }
        }
    }
}
