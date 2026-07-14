package bg.duosoft.nacid.backoffice.abdocs.domain;

import lombok.Data;

/**
 * User: ggeorgiev
 * Date: 21.01.2026
 * Time: 13:56
 */
@Data
public class SearchCorrespondentRequest {
    private Integer limit;
    private Integer offset;
    private Boolean status;
    private String identifier;
    private String name;
    private Integer correspondentGroupId;
}
