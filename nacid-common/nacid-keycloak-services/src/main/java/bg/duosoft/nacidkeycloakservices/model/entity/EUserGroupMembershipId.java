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
 * Time: 14:40
 */

@Getter
@Setter
@Embeddable
public class EUserGroupMembershipId implements Serializable {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "group_id")
    private String groupId;
}
