package bg.duosoft.nacidkeycloakservices.model.filter;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.07.2022
 * Time: 17:21
 */
@Data
public class BaseFilter {
    private String sortOrder;
    private String sortColumn;
    private Integer page;
    private Integer pageSize;
}
