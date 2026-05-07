package bg.duosoft.nacidfrontofficedto.user.filter;

import bg.duosoft.nacidfrontofficedto.Pageable;
import bg.duosoft.nacidfrontofficedto.Sortable;
import bg.duosoft.nacidfrontofficedto.utils.constants.UserSortFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserFilterDTO implements Sortable, Pageable {
    private String order = this.DESC_ORDER;
    private String orderBy = UserSortFields.DATE_CREATED;
    private Integer page = this.DEFAULT_PAGE;
    private Integer pageSize = this.DEFAULT_PAGE_SIZE;
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
