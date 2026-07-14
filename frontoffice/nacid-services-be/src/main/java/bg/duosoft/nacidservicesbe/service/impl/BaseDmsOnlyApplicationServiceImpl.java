package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.DmsApplicationInsertRequestDTO;
import bg.duosoft.nacidfrontofficedto.services.common.document.SignedApplicationDocumentDTO;
import bg.duosoft.nacidservicesbe.domain.entity.base.FullApplicationEntityBase;
import bg.duosoft.nacidservicesbe.service.BoPublicServicesAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.08.2023
 * Time: 14:14
 */
@Slf4j
public abstract class BaseDmsOnlyApplicationServiceImpl<A extends CommonApplicationDTO, AD extends CommonApplicantDetailsDTO, SD, FAE extends FullApplicationEntityBase> extends BaseApplicationServiceImpl<A, AD, SD, FAE> {

    @Autowired
    protected BoPublicServicesAdminService boPublicServicesAdminService;

    @Override
    public List<String> fileApplication(Integer id) {
        List<String> appsTempNumbers = fileApplicationInternal(id);
        sendApplicationToDms(id);

        return appsTempNumbers;
    }

    @Override
    public String fileSignedApplication(Integer id, SignedApplicationDocumentDTO signedApplication) {
        String tempNum = fileSignedApplicationInternal(id, signedApplication);
        sendApplicationToDms(id);
        return tempNum;
    }

    private void sendApplicationToDms(Integer id){
        A application = getApplication(id);

        DmsApplicationInsertRequestDTO insertRequest = new DmsApplicationInsertRequestDTO();
        insertRequest.setId(id);
        insertRequest.setApplicationType(getInitialApplicationType());
        insertRequest.setApplicationSubtype(getInitialApplicationSubtype());
        insertRequest.setCompany(application.getApplicantDetails().getApplicant().getCompany());
        insertRequest.setNaturalPerson(application.getApplicantDetails().getApplicant().getNaturalPerson());
        insertRequest.setRepresentative(application.getApplicantDetails().getRepresentative());
        insertRequest.setContactAddress(application.getApplicantDetails().getContactAddress());
        insertRequest.setAttachments(application.getDocumentDetails().getAttachments());

        Runnable insertToDmsJob = () -> {
            boPublicServicesAdminService.insertApplicationToDms(insertRequest);
        };

        Thread thread = new Thread(insertToDmsJob);
        thread.start();
    }
}
