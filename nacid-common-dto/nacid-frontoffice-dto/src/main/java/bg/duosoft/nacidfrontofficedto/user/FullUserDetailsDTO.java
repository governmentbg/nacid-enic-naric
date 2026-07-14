package bg.duosoft.nacidfrontofficedto.user;

import bg.duosoft.nacidfrontofficedto.user.access.RoleDTO;
import bg.duosoft.nacidfrontofficedto.user.access.GroupDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.07.2022
 * Time: 13:24
 */
@Data
@AllArgsConstructor
public class FullUserDetailsDTO {

    private NacidUserDetailsDTO user;
    private List<RoleDTO> roles;
    private List<GroupDTO> groups;
}
