package bg.duosoft.nacidservicesbe.service;

import bg.duosoft.nacidfrontofficedto.person.ApplicantType;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksEducationDetailsDTO;

import java.util.Map;
/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.01.2023
 * Time: 15:37
 */
public interface UniChecksService extends BaseApplicationService<UniChecksApplicationDTO, CommonApplicantDetailsDTO, UniChecksEducationDetailsDTO> {

    Map<String, String> createUniChecksFeeCalculationParamsMap(Boolean statute, Boolean authenticity, Boolean recommendation, String serviceType, ApplicantType applicantType);
}
