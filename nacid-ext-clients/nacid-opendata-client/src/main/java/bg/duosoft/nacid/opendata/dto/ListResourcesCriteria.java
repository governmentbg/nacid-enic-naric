package bg.duosoft.nacid.opendata.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * User: Georgi
 * Date: 8.9.2020 г.
 * Time: 14:15
 */
@Data
public class ListResourcesCriteria {
    private String locale;
    @JsonProperty("resource_uri")
    private String resourceUri;
    @JsonProperty("dataset_uri")
    private String datasetUri;
    private boolean reported;
    private String order;
    private String type;
    private String field;

}
