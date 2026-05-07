package bg.duosoft.nacidkeycloakservices.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.07.2022
 * Time: 14:46
 */
@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "group_role_mapping", schema = "public")
@Proxy(lazy=false)
public class EGroupRoleMapping implements Serializable {

    @EmbeddedId
    private EGroupRoleMappingId id;
}
