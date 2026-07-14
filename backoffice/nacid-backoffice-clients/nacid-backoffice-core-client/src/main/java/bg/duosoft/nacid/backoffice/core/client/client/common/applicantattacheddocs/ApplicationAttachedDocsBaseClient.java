package bg.duosoft.nacid.backoffice.core.client.client.common.applicantattacheddocs;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ApplicationAttachedDocsBaseClient {
    @GetMapping(value = "/doc/{id}")
    public AttachedDocDTO selectById(@PathVariable Integer id);
}
