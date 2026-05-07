package bg.duosoft.nacid.opendata.dto;

import lombok.Data;

/**
 * User: Georgi
 * Date: 8.9.2020 г.
 * Time: 14:14
 */
@Data
public class ListResourcesRequest extends OpenDataRequest{
    private ListResourcesCriteria criteria;
}
