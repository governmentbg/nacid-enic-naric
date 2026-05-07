package bg.duosoft.nacid.backoffice.core.data.mapper.fo;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.CertificateReceiveFormType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.application.FoApplicationFlagsMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.application.FoDocumentReceiveMethodMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.common.FoReferenceDataMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.diploma_name.FoApplicantDiplomaNamesMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.person.FoPersonsMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.person.address.FoContactAddressMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.person.address.FoReceiverAddressMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.person.NaturalPersonNamesDTO;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.RudiApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.SeApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatus;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofEducationDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksEducationDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FoApplicationDataConverter {

    private final FoReferenceDataMapper referenceDataMapper;
    private final FoDocumentReceiveMethodMapper documentReceiveMethodMapper;
    private final FoApplicationFlagsMapper applicationFlagsMapper;
    private final FoContactAddressMapper contactAddressMapper;
    private final FoReceiverAddressMapper receiverAddressMapper;
    private final FoPersonsMapper personsMapper;
    private final FoApplicantDiplomaNamesMapper applicantDiplomaNamesMapper;

    public void setCommonApplicationData(ApplicationDTO application, CommonApplicationDTO foApp) {
        if (Objects.nonNull(foApp)) {
            CommonApplicantDetailsDTO applicantDetails = foApp.getApplicantDetails();
            setCommonData(application, foApp);
            setServiceType(application, foApp);
            setDocumentReceiveMethod(application, foApp);
            if (Objects.nonNull(applicantDetails)) {
                personsMapper.overrideApplicationPersons(applicantDetails, application);
                applicationFlagsMapper.overrideFlagsData(applicantDetails, application);
                setContactAddress(application, applicantDetails);
                setApplicantDiplomaNames(application, applicantDetails);
            }
        }
    }

    private void setDocumentReceiveMethod(ApplicationDTO application, CommonApplicationDTO foApp) {
        CommonApplicantDetailsDTO applicantDetails = foApp.getApplicantDetails();
        if (Objects.nonNull(applicantDetails)) {
            application.setDocumentReceiveMethods(new ArrayList<>());
            List<String> certificateReceiveForms = applicantDetails.getCertificateReceiveForms();
            if (!CollectionUtils.isEmpty(certificateReceiveForms)) {
                for (String certificateReceiveForm : certificateReceiveForms) {
                    if (certificateReceiveForm.equals(CertificateReceiveFormType.PAPER.code())) {
                        initDocumentReceiveMethod(application.getDocumentReceiveMethods(), applicantDetails.getResultReceivePaper(), certificateReceiveForm);
                    } else {
                        initDocumentReceiveMethod(application.getDocumentReceiveMethods(), applicantDetails.getResultReceiveElectronic(), certificateReceiveForm);
                    }
                }
            }
        }
    }

    private void initDocumentReceiveMethod(List<ApplicationDocumentReceiveMethodDTO> applicationDocumentReceiveMethods, bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationDocumentReceiveMethodDTO foDocumentReceiveMethod, String certificateReceiveForm) {
        DocumentReceiveMethodDTO documentReceiveMethod = new DocumentReceiveMethodDTO();
        AddressDTO documentRecipientAddress;
        ApplicationDocumentReceiveMethodDTO applicationDocumentReceiveMethod = new ApplicationDocumentReceiveMethodDTO();
        if (StringUtils.hasText(certificateReceiveForm)) {
            applicationDocumentReceiveMethod.setCrfCode(new bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO(ReferenceDataDomain.CERTIFICATE_RECEIVE_FORM.domain(), certificateReceiveForm));
        }

        documentReceiveMethod.setId(foDocumentReceiveMethod.getResultReceive().getId());
        documentRecipientAddress = receiverAddressMapper.toReceiverAddress(foDocumentReceiveMethod.getReceiverAddress());

        applicationDocumentReceiveMethod.setDocumentReceiveMethod(documentReceiveMethod);
        applicationDocumentReceiveMethod.setDocumentRecipientAddress(documentRecipientAddress);
        applicationDocumentReceiveMethods.add(applicationDocumentReceiveMethod);
    }


    private void setCommonData(ApplicationDTO application, CommonApplicationDTO foApp) {
        application.setEfilingId(foApp.getId());
        application.setEfilingSignedFlag(foApp.getFoStatus() == FoApplicationStatus.SUBMITTED_WITH_SIGNATURE);
        application.setEntryNumber(foApp.getEntryNumber());
        application.setEntryDate(foApp.getEntryDate());

        CommonApplicantDetailsDTO applicantDetails = foApp.getApplicantDetails();
        if (Objects.nonNull(applicantDetails)) {
            application.setRepresentativeCapacity(applicantDetails.getRepresentativeCapacity());
        }
    }

    private void setServiceType(ApplicationDTO application, CommonApplicationDTO foApp) {
        ReferenceDataDTO serviceType = null;
        if (foApp instanceof UniChecksApplicationDTO sarApplication) {
            UniChecksEducationDetailsDTO sarEducationDetails = sarApplication.getEducationDetails();
            if (Objects.nonNull(sarEducationDetails)) {
                serviceType = sarEducationDetails.getServiceType();
            }
        } else if (foApp instanceof RegprofApplicationDTO regprofApplication) {
            RegprofEducationDetailsDTO regprofEducationDetails = regprofApplication.getEducationDetails();
            if (Objects.nonNull(regprofEducationDetails)) {
                serviceType = regprofEducationDetails.getServiceType();
            }
        }

        application.setServiceType(referenceDataMapper.toReferenceData(serviceType));
    }

    private void setContactAddress(ApplicationDTO application, CommonApplicantDetailsDTO applicantDetails) {
        application.setContactAddress(contactAddressMapper.toContactAddress(applicantDetails.getContactAddress()));
    }

    private void setApplicantDiplomaNames(ApplicationDTO application, CommonApplicantDetailsDTO applicantDetails) {
        NaturalPersonNamesDTO applicantDiplomaNames = null;
        if (applicantDetails instanceof RudiApplicantDetailsDTO rudiApplicantDetails) {
            applicantDiplomaNames = rudiApplicantDetails.getDiplomaNames();
        } else if (applicantDetails instanceof RegprofApplicantDetailsDTO regprofApplicantDetails) {
            applicantDiplomaNames = regprofApplicantDetails.getQualificationNames();
        } else if (applicantDetails instanceof SeApplicantDetailsDTO seApplication) {
            applicantDiplomaNames = seApplication.getDiplomaNames();
        }

        application.setApplicantDiplomaNames(applicantDiplomaNamesMapper.toApplicantDiplomaNames(applicantDiplomaNames));
    }
}
