package bg.duosoft.nacid.opendata.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * User: Georgi
 * Date: 8.9.2020 г.
 * Time: 11:40
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenDataUser {
    private String id;
    private String username;
    private String email;
}
