package bg.duosoft.nacidfrontofficedto.person;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.05.2022
 * Time: 11:41
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaturalPersonNamesDTO {

    private String firstName;
    private String middleName;
    private String lastName;
    private String otherName;
    private String latinFirstName;
    private String latinMiddleName;
    private String latinLastName;
    private String latinOtherName;
}
