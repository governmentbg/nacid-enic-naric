package bg.duosoft.nacid.backoffice.core.client.client.common.applications;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.05.2023
 * Time: 16:47
 */
public interface ApplicationsBaseClient {

    @GetMapping(value = "/status-by-entry-details")
    String getStatusCodeByEntryDetails(@RequestParam("entryNumber") String entryNumber,
                                       @DateTimeFormat(pattern = "dd.MM.yyyy") @RequestParam("entryDate") LocalDate entryDate);

    @GetMapping(value = "/responsible-user-by-backoffice-number")
    String getApplicationResponsibleUserByBackofficeNumber(@RequestParam("backofficeNumber") String backofficeNumber);
}
