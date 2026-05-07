package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.abdocs.domain.DocFile;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.DownloadFileResponse;
import bg.duosoft.nacid.backoffice.core.be.service.common.AbdocsCoreService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICATION_ABDOCS_FILE_TRANSFER)
@RequestMapping("/api/v1/abdocs-docs")
public class AbdocsController extends BaseAccessController {
    private final AbdocsCoreService abdocsCoreService;

    @Override
    public String getEditRole() {
        return null;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @GetMapping(value = "/{docflowId}")
    public Doc selectAbdocsDoc(@PathVariable Integer docflowId) {
        return abdocsCoreService.selectAbdocsDoc(docflowId);
    }

    @GetMapping(value = "/{docflowId}/file/{fileId}")
    public ResponseEntity<byte[]> getAbdocsFileContent(@PathVariable Integer docflowId, @PathVariable Integer fileId) {
        DocFile docFile = abdocsCoreService.selecAbdocsDocFile(docflowId, fileId);
        DownloadFileResponse abdocsFile = abdocsCoreService.getAbdocsDownloadFile(docFile);
        if (Objects.isNull(abdocsFile)) {
            return ResponseEntity.notFound().build();
        }

        String fileNameEncoded = URLEncoder.encode(docFile.getName(), StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .header("Content-Disposition", String.format("%s;filename*=UTF-8''%s;filename=\"%s\"", "attachment", fileNameEncoded, fileNameEncoded))
                .contentType(MediaType.valueOf(abdocsFile.getType())).body(abdocsFile.getContent());

    }
}
