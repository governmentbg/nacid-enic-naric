package bg.duosoft.nacidfrontofficedto.user.access;

import bg.duosoft.nacidfrontofficedto.user.BaseUserDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 18.07.2022
 * Time: 18:38
 */
@Data
@AllArgsConstructor
public class FullRoleDetailsDTO {

    private RoleDTO role;
    private List<BaseUserDetailsDTO> users;
    private Map<String, List<String>> groupUsers;
}
