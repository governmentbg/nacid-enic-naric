package bg.duosoft.nacid.opendata.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * User: Georgi
 * Date: 8.9.2020 г.
 * Time: 14:15
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ListResourcesResponse extends OpenDataResponse{
    private List<OpenDataResource> resources;
}
