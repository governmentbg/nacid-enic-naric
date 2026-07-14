package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocumentReceiveMethodDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentReceiveMethodFormDTO implements Serializable {
    private List<String> crfCodes;
    private ApplicationDocumentReceiveMethodDTO electronicReceivedMethod;
    private ApplicationDocumentReceiveMethodDTO paperReceivedMethod;
}
