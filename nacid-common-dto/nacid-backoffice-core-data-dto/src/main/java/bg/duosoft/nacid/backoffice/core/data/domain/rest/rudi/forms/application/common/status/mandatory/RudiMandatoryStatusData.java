package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.mandatory;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocflowStatusHistoryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationNormalStatusHistoryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LegalReasonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;

import java.util.List;

public interface RudiMandatoryStatusData {
    Integer getApplicationId();

    ReferenceDataDTO getStatus();

    LegalReasonDTO getLegalReason();

    String getSubmittedDocs();

    ReferenceDataDTO getDocflowStatus();

    String getArchiveNumber();

    List<ApplicationNormalStatusHistoryDTO> getStatusHistory();

    List<ApplicationDocflowStatusHistoryDTO> getDocflowStatusHistory();

    void setApplicationId(Integer applicationId);

    void setStatus(ReferenceDataDTO status);

    void setLegalReason(LegalReasonDTO legalReason);

    void setSubmittedDocs(String submittedDocs);

    void setDocflowStatus(ReferenceDataDTO docflowStatus);

    void setArchiveNumber(String archiveNumber);

    void setStatusHistory(List<ApplicationNormalStatusHistoryDTO> statusHistory);

    void setDocflowStatusHistory(List<ApplicationDocflowStatusHistoryDTO> docflowStatusHistory);
}
