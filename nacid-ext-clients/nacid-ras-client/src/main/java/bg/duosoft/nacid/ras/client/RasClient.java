package bg.duosoft.nacid.ras.client;


import bg.duosoft.nacid.ras.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * User: ggeorgiev
 * Date: 08.06.2023
 */
@FeignClient(name = "RasClient", url = "${feign.ras.base-url}")
public interface RasClient {

    @GetMapping("/Public/CheckUin/{uin}")
    public CheckUinResponse checkUin(@PathVariable("uin") String uin);

    @GetMapping("/Nomenclatures/Person/ResearchArea")
    public GetNomenclaturesResult getResearchAreas(@RequestParam("limit")int limit);

    @GetMapping("/Nomenclatures/Country")
    public GetNomenclaturesResult getCountries(@RequestParam("limit")int limit);

    @GetMapping("/Nomenclatures/Language")
    public GetNomenclaturesResult getLanguages(@RequestParam("limit")int limit);


    @PostMapping("/Public/Application")
    public RegisterApplicationResponse registerApplication(@RequestBody RegisterApplicationRequest request);

    @PostMapping(value = "/FilesStorage", consumes = "multipart/form-data")
    public FileStorageResponse filesStorage(@RequestPart("file") MultipartFile attachment);

}
