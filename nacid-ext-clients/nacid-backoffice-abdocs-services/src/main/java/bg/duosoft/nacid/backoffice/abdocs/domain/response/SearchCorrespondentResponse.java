package bg.duosoft.nacid.backoffice.abdocs.domain.response;

import bg.duosoft.nacid.backoffice.abdocs.domain.Correspondent;
import lombok.Data;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 21.01.2026
 * Time: 13:59
 */
@Data
public class SearchCorrespondentResponse {
    private List<Correspondent> correspondents;
}
