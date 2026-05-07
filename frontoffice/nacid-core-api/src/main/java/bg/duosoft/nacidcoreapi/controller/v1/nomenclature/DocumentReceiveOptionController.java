package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;

import bg.duosoft.nacidcoreapi.service.nomenclature.DocumentReceiveOptionService;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocumentReceiveOptionDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_DOCUMENT_RECEIVE_OPTIONS)
@RequestMapping("/api/v1/document-receive-options")
public class DocumentReceiveOptionController {

    private final DocumentReceiveOptionService service;

    @GetMapping("/by-kind")
    public List<DocumentReceiveOptionDTO> getAllByKind(@RequestParam String kind) {
        return service.selectByKind(kind);
    }

}
