package bg.duosoft.nacidservicesbe.service;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNoteKind;
import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNotesApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNotesDetailsDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.02.2023
 * Time: 14:15
 */
public interface OfficialNotesService extends BaseApplicationService<OfficialNotesApplicationDTO, CommonApplicantDetailsDTO, OfficialNotesDetailsDTO> {

    Map<String, String> createOfficialNotesFeeCalculationParamsMap(String serviceType, List<OfficialNoteKind> kinds);
}
