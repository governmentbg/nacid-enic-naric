package bg.duosoft.nacidshareddata.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.07.2022
 * Time: 11:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserDetails {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
