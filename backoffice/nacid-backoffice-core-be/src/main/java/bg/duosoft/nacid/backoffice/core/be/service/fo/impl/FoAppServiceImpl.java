package bg.duosoft.nacid.backoffice.core.be.service.fo.impl;

import bg.duosoft.nacid.backoffice.core.be.service.fo.FoAppService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import bg.duosoft.nacidservicesclient.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoAppServiceImpl implements FoAppService {

    private final ServicesDocDegreesClient servicesDocDegreesClient;
    private final ServicesHeRecognitionClient servicesHeRecognitionClient;
    private final ServicesUniChecksClient servicesUniChecksClient;
    private final ServicesRegprofClient servicesRegprofClient;
    private final ServicesBibliographicReferenceClient servicesBibliographicReferenceClient;
    private final ServicesInquiryClient servicesInquiryClient;
    private final ServicesOfficialNotesClient servicesOfficialNotesClient;
    private final ServicesDocDeliveryClient servicesDocDeliveryClient;

    @Override
    public CommonApplicationDTO selectFoApplication(Integer foAppId, String appType, String subType) {
        if (Objects.isNull(foAppId)) {
            return null;
        }

        ApplicationSubType type = ApplicationSubType.selectByTypeAndSubType(appType, subType);
        switch (type) {
            case RUDI_UNI_DIPLOMA_RECOGNITION -> {
                return servicesHeRecognitionClient.getApplication(foAppId);
            }
            case RUDI_SAR -> {
                return servicesUniChecksClient.getApplication(foAppId);
            }
            case RUDI_DOC_DEGREE_RECOGNITION -> {
                return servicesDocDegreesClient.getApplication(foAppId);
            }
            case REGPROF_EDU, REGPROF_EDU_AND_INTERNSHIP, REGPROF_INTERNSHIP -> {
                return servicesRegprofClient.getApplication(foAppId);
            }
            case LIBSERV_BIBLIOGRAPHIC_REFERENCE -> {
                return servicesBibliographicReferenceClient.getApplication(foAppId);
            }
            case LIBSERV_INQUIRY -> {
                return servicesInquiryClient.getApplication(foAppId);
            }
            case LIBSERV_OFFICIAL_NOTE -> {
                return servicesOfficialNotesClient.getApplication(foAppId);
            }
            case LIBSERV_DOCUMENT_DELIVERY -> {
                return servicesDocDeliveryClient.getApplication(foAppId);
            }
        }

        return null;
    }
}
