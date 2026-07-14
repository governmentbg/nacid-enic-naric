package bg.duosoft.nacidcoreclient.client.documentReceiveMethod;

import bg.duosoft.nacidfrontofficedto.nomenclature.DocumentReceiveMethodDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BaseDocumentReceiveMethodClient {

    @GetMapping
    List<DocumentReceiveMethodDTO> selectDocumentReceiveMethods();

    @GetMapping(value = "/{id}")
    DocumentReceiveMethodDTO getById(@PathVariable("id") String id);
}
