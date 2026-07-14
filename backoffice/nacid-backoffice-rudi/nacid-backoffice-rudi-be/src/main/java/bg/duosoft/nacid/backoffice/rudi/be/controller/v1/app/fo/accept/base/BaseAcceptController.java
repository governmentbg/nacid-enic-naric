package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.fo.accept.base;

import bg.duosoft.nacid.backoffice.core.client.client.common.person.AdminPersonClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CivilIdTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.accept.RudiAcceptBaseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.accept.RudiAcceptViewDataBaseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.accept.DocrecAcceptDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.accept.SarAcceptDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.udirec.accept.UdirecAcceptDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.docrec.DocrecAcceptanceMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.sar.SarAcceptanceMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.udirec.UdirecAcceptanceMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.FoConverter;
import bg.duosoft.nacid.backoffice.rudi.be.service.AcceptanceService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.service.UniversityFacultyService;
import bg.duosoft.nacid.backoffice.rudi.be.service.UniversityService;
import bg.duosoft.nacidbackofficeshareddata.service.FoAcceptAppRequirementsService;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.UniversityDataDTO;
import bg.duosoft.nacidfrontofficedto.services.docdegrees.DocDegreesApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.herecognition.HeRecognitionApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksApplicationDTO;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class BaseAcceptController extends BaseAccessController {

    @Autowired
    private DocrecAcceptanceMapper docrecAcceptanceMapper;

    @Autowired
    private UdirecAcceptanceMapper udirecAcceptanceMapper;

    @Autowired
    private SarAcceptanceMapper sarAcceptanceMapper;

    @Autowired
    private AcceptanceService acceptanceService;

    @Autowired
    protected FoAcceptAppRequirementsService foAcceptAppRequirementsService;

    @Autowired
    protected RudiApplicationService rudiApplicationService;

    @Autowired
    private FoConverter foConverter;

    @Autowired
    private AdminPersonClient adminPersonClient;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private UniversityFacultyService universityFacultyService;

    protected IntegerIdDTO processAcceptance(RudiAcceptBaseDTO requestData, CommonApplicationDTO foApplication) {
        RudiApplicationDTO application = convertToBackofficeApplication(foApplication);

        if (requestData instanceof DocrecAcceptDTO receptionDTO) {
            docrecAcceptanceMapper.overrideData(receptionDTO, application);
            if (foApplication instanceof DocDegreesApplicationDTO docrecApp) {
                application.getTrainingCourse().setTrainingCourseUniversities(generateTCUniversities(docrecApp.getEducationDetails().getUniversitiesData(), application));
            }
        } else if (requestData instanceof SarAcceptDTO receptionDTO) {
            sarAcceptanceMapper.overrideData(receptionDTO, application);
            if (foApplication instanceof UniChecksApplicationDTO sarApp) {
                application.getTrainingCourse().setTrainingCourseUniversities(generateTCUniversities(sarApp.getEducationDetails().getUniversitiesData(), application));
            }
        } else if (requestData instanceof UdirecAcceptDTO receptionDTO) {
            udirecAcceptanceMapper.overrideData(receptionDTO, application);
            if (foApplication instanceof HeRecognitionApplicationDTO udirecApp) {
                application.getTrainingCourse().setTrainingCourseUniversities(generateTCUniversities(udirecApp.getEducationDetails().getUniversitiesData(), application));
            }
        }

        RudiApplicationDTO reception = acceptanceService.acceptApplication(application);
        return new IntegerIdDTO(reception.getApplication().getId());
    }

    //dobavq suvmestnite universiteti i izbranite faculteti
    //faculteta trqbva da se zapazi predi da se mapne kum app-a poneje e nujno da ima id
    private List<TrainingCourseUniversityDTO> generateTCUniversities(List<UniversityDataDTO> foUniversities, RudiApplicationDTO app) {
        ArrayList<TrainingCourseUniversityDTO> trainingCourseUniversities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(foUniversities)) {
            for (int i = 0; i < foUniversities.size(); i++) {
                UniversityDataDTO uniData = foUniversities.get(i);
                if (Objects.nonNull(uniData.getNameId())) {
                    TrainingCourseUniversityDTO trainingCourseUniversity = new TrainingCourseUniversityDTO();
                    UniversityDTO university = universityService.selectById(uniData.getNameId());

                    trainingCourseUniversity.setOrdNum(i == 0 ? 1 : 2);
                    trainingCourseUniversity.setUniversity(university);
                    trainingCourseUniversity.setCountry(university.getCountry());
                    trainingCourseUniversity.setUniversityNameTranslated(university.getBgName());
                    trainingCourseUniversity.setUniversityContact(foUniversities.get(i).getUniversityContact());

                    if (Objects.nonNull(uniData.getFacultyId())) {
                        trainingCourseUniversity.setFaculty(universityFacultyService.selectFacultyById(uniData.getFacultyId()));
                    } else if (StringUtils.hasText(uniData.getFaculty())) {
                        List<FacultyDTO> faculties = universityFacultyService.selectUniversityFacultiesByUniversityIdAndName(university.getId(), uniData.getFaculty());
                        if (CollectionUtils.isEmpty(faculties)) {
                            trainingCourseUniversity.setFaculty(universityFacultyService.create(new FacultyDTO(null, uniData.getFaculty(), null, true, 0), university));
                        } else {
                            trainingCourseUniversity.setFaculty(faculties.get(0));
                        }
                    }
                    trainingCourseUniversities.add(trainingCourseUniversity);
                } else {
                    if (i == 0) {
                        app.getTrainingCourse().setManualTempUniName(foUniversities.get(i).getName());
                    }
                }
            }
        }
        return CollectionUtils.isEmpty(trainingCourseUniversities) ? null : trainingCourseUniversities;
    }

    @NotNull
    protected RudiApplicationDTO convertToBackofficeApplication(CommonApplicationDTO foApplication) {
        return foConverter.convertApplication(foApplication);
    }

    protected void fillBaseViewData(RudiApplicationDTO rudiApplication, RudiAcceptViewDataBaseDTO viewData) {
        ApplicationDTO application = rudiApplication.getApplication();
        if (Objects.nonNull(application)) {
            viewData.setApplicant(application.getApplicant());
            viewData.setRepresentative(application.getRepresentative());
            viewData.setRepresentativeCompany(application.getRepresentativeCompany());
            viewData.setContactAddress(application.getContactAddress());
        }

        TrainingCourseDTO trainingCourse = rudiApplication.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            if (Objects.nonNull(trainingCourse.getBaseUniversity())) {
                viewData.setBaseUniversity(trainingCourse.getBaseUniversity());
            }
            viewData.setOriginalEduLevelTranslated(trainingCourse.getOriginalEduLevelTranslated());
            viewData.setOriginalEduLevelName(trainingCourse.getOriginalEduLevelName());
            viewData.setDiplomaOwnerEan(trainingCourse.getDiplomaOwnerEan());
            viewData.setDiplomaDate(trainingCourse.getDiplomaDate());
        }
    }

    protected void setInitialAcceptData(RudiAcceptBaseDTO acceptBaseDTO, RudiApplicationDTO convertedApplication) {
        ApplicationDTO application = convertedApplication.getApplication();
        if (Objects.nonNull(application)) {

            PersonDTO applicant = getExistingBackofficePerson(application.getApplicant());
            if (Objects.nonNull(applicant)) {
                acceptBaseDTO.setApplicantId(applicant.getId());
            }

            PersonDTO representative = getExistingBackofficePerson(application.getRepresentative());
            if (Objects.nonNull(representative)) {
                acceptBaseDTO.setRepresentativeId(representative.getId());

                PersonDTO representativeCompany = getExistingBackofficePerson(application.getRepresentativeCompany());
                if (Objects.nonNull(representativeCompany)) {
                    acceptBaseDTO.setRepresentativeCompanyId(representativeCompany.getId());
                    acceptBaseDTO.setRepresentativeCompanyFlag(true);
                }
            }

        }
        setExistingUniversity(convertedApplication, acceptBaseDTO);
    }

    public PersonDTO getExistingBackofficePerson(PersonDTO person) {
        if (Objects.isNull(person)) {
            return null;
        }

        try {
            String civilIdTypeId = null;
            CivilIdTypeDTO civilIdType = person.getCivilIdType();
            if (Objects.nonNull(civilIdType)) {
                civilIdTypeId = civilIdType.getId();
            }

            String foreignId = null;
            ReferenceDataDTO foreignIdentifierType = person.getForeignIdentifierType();
            if (Objects.nonNull(foreignIdentifierType)) {
                foreignId = foreignIdentifierType.getId();
            }

            String foreignIdCountry = null;
            CountryDTO foreignIdentifierCountry = person.getForeignIdentifierCountry();
            if (Objects.nonNull(foreignIdentifierCountry)) {
                foreignIdCountry = foreignIdentifierCountry.getId();
            }

            List<PersonDTO> persons = adminPersonClient.searchByCivilId(civilIdTypeId, person.getCivilId(), foreignId, foreignIdCountry, true);
            if (!CollectionUtils.isEmpty(persons) && persons.size() == 1) {
                return persons.get(0);
            }

            return null;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    private void setExistingUniversity(RudiApplicationDTO convertedApplication, RudiAcceptBaseDTO acceptDTO) {
        TrainingCourseDTO trainingCourse = convertedApplication.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            UniversityDTO baseUniversity = trainingCourse.getBaseUniversity();
            if (Objects.nonNull(baseUniversity)) {
                Integer id = baseUniversity.getId();
                if (Objects.nonNull(id)) {
                    acceptDTO.setBaseUniversityId(id);
                } else {
                    List<UniversityDTO> dbUniversities = universityService.selectUniversityByBgNameExact(baseUniversity.getBgName());
                    if (!CollectionUtils.isEmpty(dbUniversities) && dbUniversities.size() == 1) {
                        acceptDTO.setBaseUniversityId(dbUniversities.get(0).getId());
                    }
                }
            }
        }
    }

    @Override
    public String getEditRole() {
        return SecurityRole.FO_APPS_ACCEPTANCE_ACCESS;
    }

    @Override
    public String getAccessRole() {
        return SecurityRole.FO_APPS_ACCEPTANCE_ACCESS;
    }
}
