package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocumentReceiveOptionDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentReceiveOptionFormDTO implements Serializable {
    private ApplicationDocumentReceiveOptionDTO receiveOption;
}
