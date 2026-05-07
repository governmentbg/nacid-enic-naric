package bg.duosoft.nacidcoreapi.integration.naciddoc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.03.2024
 * Time: 18:36
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NacidDocumentDetails {

    private Integer id;
    private String description;
    private List<NacidDocument> documents;

}
