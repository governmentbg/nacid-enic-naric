package bg.duosoft.nacidbackofficeshareddata.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;

import java.time.LocalDate;

public interface BaseApplicationService {
    ApplicationDTO selectApplicationById(Integer id);

    ApplicationDTO selectApplicationByEntryNumberAndDate(String entryNumber, LocalDate entryDate);

    boolean isFoAppAlreadyAccepted(Integer efilingId);

    public LocalDate getExecutionPeriodEnd(Integer applicationId);

}
