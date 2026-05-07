package bg.duosoft.nacid.backoffice.rudi.be.controller.v1;

import bg.duosoft.nacid.backoffice.abdocs.domain.DocFile;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.DocrecRasInfoDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.ras.RasTransferDto;
import bg.duosoft.nacid.backoffice.rudi.be.service.RasService;
import bg.duosoft.nacidshareddata.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 09.06.2023
 * Time: 13:43
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ras")
public class RasController {
    private final RasService rasService;

    @GetMapping("/info/{id}")
    public DocrecRasInfoDTO selectApplicationRasInfo(@PathVariable Integer id) {
        return rasService.selectRasApplicationInfo(id);
    }

    @GetMapping("/certificate/public-files/{id}")
    public List<DocFile> selectCertificatePublicFiles(@PathVariable Integer id) {
        return ResponseUtils.notFoundCheck(rasService.selectCertificateFiles(id));
    }

    @PostMapping("/register/{id}")
    public void registerApplicationInRas(@PathVariable Integer id, @RequestBody RasTransferDto rasTransferDto) {
        rasService.registerRasApplication(id, rasTransferDto.getCertPublicFileId());
    }
}
