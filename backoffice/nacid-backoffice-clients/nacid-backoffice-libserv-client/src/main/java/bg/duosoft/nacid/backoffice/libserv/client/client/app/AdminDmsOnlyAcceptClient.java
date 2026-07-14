package bg.duosoft.nacid.backoffice.libserv.client.client.app;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.DmsOnlyApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.DmsOnlyFoDataUpdateDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.DmsOnlyFoReceiptSaveDTO;
import bg.duosoft.nacid.backoffice.libserv.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.08.2023
 * Time: 18:06
 */
@FeignClient(name = "AdminDmsOnlyAcceptClient", url = "${feign.backoffice-libserv.base-url}/v1/fo-applications/accept/dms-only", configuration = ClientTokenFeignConfig.class)
public interface AdminDmsOnlyAcceptClient {

    @PostMapping
    void accept(@RequestBody DmsOnlyApplicationDTO dmsOnlyApplication);

    @PostMapping("/error-log/registration")
    void errorLogRegistration(@RequestBody DmsOnlyApplicationDTO dmsOnlyApplication);

    @PostMapping("/error-log/update-fo-data")
    void errorLogUpdateFoData(@RequestBody DmsOnlyFoDataUpdateDTO foDataUpdate);

    @PostMapping("/error-log/save-fo-receipt")
    void errorLogSaveFoReceipt(@RequestBody DmsOnlyFoReceiptSaveDTO receiptSave);
}
