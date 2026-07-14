package bg.duosoft.nacidfrontofficedto.user.access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.07.2022
 * Time: 10:38
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class RoleDTO {

    protected String id;
    protected String name;
    protected String description;
}
