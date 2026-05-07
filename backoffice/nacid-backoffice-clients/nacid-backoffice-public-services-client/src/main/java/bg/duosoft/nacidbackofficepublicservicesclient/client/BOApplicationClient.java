package bg.duosoft.nacidbackofficepublicservicesclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.05.2023
 * Time: 17:05
 */
@FeignClient(name = "BOApplicationClient", url = "${feign.backoffice-public-services.base-url}/v1/application")
public interface BOApplicationClient {

    @GetMapping(value = "/not-denied-by-entry-details")
    Boolean appNotDeniedForEntryDetails(@RequestParam("entryNumber") String entryNumber,
                                        @DateTimeFormat(pattern = "dd.MM.yyyy") @RequestParam("entryDate") LocalDate entryDate);

    @GetMapping(value = "/se-recognition/exists")
    Boolean seRecognitionExistsByEntryDetails(@RequestParam("entryNumber") String entryNumber,
                                   @DateTimeFormat(pattern = "dd.MM.yyyy") @RequestParam("entryDate") LocalDate entryDate);
    @GetMapping(value = "/exists/by-app-type")
    Boolean hasApplicationByEntryDetailsAndAppType(@RequestParam("entryNumber") String entryNumber,
                                                          @DateTimeFormat(pattern = "dd.MM.yyyy") @RequestParam("entryDate") LocalDate entryDate,
                                                          @RequestParam("appType") String appType);
}
