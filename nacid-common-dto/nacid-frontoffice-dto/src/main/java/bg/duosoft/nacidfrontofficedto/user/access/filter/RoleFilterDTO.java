package bg.duosoft.nacidfrontofficedto.user.access.filter;

import bg.duosoft.nacidfrontofficedto.Pageable;
import bg.duosoft.nacidfrontofficedto.Sortable;
import bg.duosoft.nacidfrontofficedto.utils.constants.RoleSortFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleFilterDTO implements Sortable, Pageable {
    private String order = this.DESC_ORDER;
    private String orderBy = RoleSortFields.NAME;
    private Integer page = this.DEFAULT_PAGE;
    private Integer pageSize = this.DEFAULT_PAGE_SIZE;
    private String name;
    private String description;
    private String excludedUserId;
    private String excludedGroupId;
}
