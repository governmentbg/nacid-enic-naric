package bg.duosoft.nacid.backoffice.core.data.domain.security;

import lombok.Builder;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 22.06.2022
 * Time: 15:08
 */
@Data
@Builder
public class NacidUserDetails {

    private String username;
    private String locale;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String personalIdType;
    private String personalId;
}
