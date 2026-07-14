package bg.duosoft.nacidcoreapi.integration.naciddoc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.03.2024
 * Time: 18:38
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NacidDocument {

    private Integer id;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("file_url")
    private String fileUrl;

    private String type;

}
