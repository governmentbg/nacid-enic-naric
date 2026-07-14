
package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocCaseLinkDO {
    private boolean createDocCaseLink = true; // Трябва да е true
    private String registerName = "backoffice"; // Трябва да е "backoffice"
}

//    Integer entityid // nullable