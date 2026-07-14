package bg.duosoft.nacidbackofficeshareddata.service;


import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.AcceptApplicationRequestDTO;

public interface FoAcceptAppExtraDataService {

    void updateFrontOfficeData(ApplicationDTO application);

    void updateFrontOfficeData(AcceptApplicationRequestDTO acceptApplicationRequest);

    void updateRegprofFrontOfficeData(ApplicationDTO application, boolean isApostille);

    void updatePaymentsData(ApplicationDTO application);
}
