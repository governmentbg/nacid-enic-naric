package bg.duosoft.nacid.backoffice.abdocs.domain.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class DocCreationResponse {

    private List<Integer> createdDocumentIds;

}
