package bg.duosoft.nacidfrontofficedto.user.access;

import bg.duosoft.nacidfrontofficedto.user.BaseUserDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 22.07.2022
 * Time: 15:49
 */
@Data
@AllArgsConstructor
public class FullGroupDetailsDTO {

    private GroupDTO group;
    private List<GroupDTO> parentGroups;
    private List<GroupDTO> childrenGroups;
    private List<BaseUserDetailsDTO> users;
    private List<RoleDTO> roles;
    private Map<String, List<RoleDTO>> inheritedRoles;
}
