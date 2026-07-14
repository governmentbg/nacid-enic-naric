package bg.duosoft.nacidkeycloakservices.model.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserFilter extends BaseFilter {

    private String name;
    private String fullName;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Boolean enabled;
    private Boolean emailVerified;
    private String excludedGroupId;
    private String excludedRoleId;
    private List<String> excludedUsernames;
    private List<String> includedRoles;
    private List<String> groupNames;
}
