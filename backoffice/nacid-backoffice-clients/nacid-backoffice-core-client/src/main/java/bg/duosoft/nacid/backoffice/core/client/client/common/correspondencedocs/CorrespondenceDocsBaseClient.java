package bg.duosoft.nacid.backoffice.core.client.client.common.correspondencedocs;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface CorrespondenceDocsBaseClient {
    @GetMapping("/abdocs-doc-by-attached-doc-id")
    Doc selectAbdocsDocByAttachedDocId(@RequestParam Integer attachedDocId);

    @PostMapping("/process-correspondence-docs")
    void processCorrespondenceDocs();
}
