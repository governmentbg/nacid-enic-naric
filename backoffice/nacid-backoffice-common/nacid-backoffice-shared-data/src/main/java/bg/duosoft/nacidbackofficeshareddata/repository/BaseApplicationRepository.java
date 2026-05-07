package bg.duosoft.nacidbackofficeshareddata.repository;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationEntity;

import java.time.LocalDate;

public interface BaseApplicationRepository {
    ApplicationEntity selectApplicationById(Integer applicationId);

    ApplicationEntity selectApplicationByEntryNumberAndDate(String entryNumber, LocalDate entryDate);

    public LocalDate getExecutionPeriodEnd(Integer applicationId);

    boolean isFoAppAlreadyAccepted(Integer efilingId);

}
