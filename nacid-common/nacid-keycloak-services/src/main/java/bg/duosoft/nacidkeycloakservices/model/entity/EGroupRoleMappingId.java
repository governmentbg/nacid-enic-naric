package bg.duosoft.nacidkeycloakservices.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.07.2022
 * Time: 14:47
 */
@Getter
@Setter
@Embeddable
public class EGroupRoleMappingId implements Serializable {

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "role_id")
    private String roleId;
}
