package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.common.document.SignedApplicationDocumentDTO;
import bg.duosoft.nacidservicesbe.domain.entity.base.FullApplicationEntityBase;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.08.2023
 * Time: 14:17
 */
public abstract class BaseCommonApplicationServiceImpl<A extends CommonApplicationDTO, AD extends CommonApplicantDetailsDTO, SD, FAE extends FullApplicationEntityBase> extends BaseApplicationServiceImpl<A, AD, SD, FAE> {

    @Override
    public List<String> fileApplication(Integer id) {
        return fileApplicationInternal(id);
    }

    @Override
    public String fileSignedApplication(Integer id, SignedApplicationDocumentDTO signedApplication) {
        return fileSignedApplicationInternal(id, signedApplication);
    }
}
