package bg.duosoft.nacidfrontofficedto.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class BaseUserDetailsDTO {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean emailVerified;
    private Boolean enabled;

    public String getFullName(){
        return String.format("%s %s", firstName, lastName);
    }
}
