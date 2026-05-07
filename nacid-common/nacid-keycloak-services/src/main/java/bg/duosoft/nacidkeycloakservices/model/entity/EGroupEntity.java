package bg.duosoft.nacidkeycloakservices.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "keycloak_group", schema = "public")
@Proxy(lazy = false)
public class EGroupEntity implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "realm_id")
    private String realmId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private List<EGroupAttribute> groupAttributes;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_group")
    private EGroupEntity parentGroup;

}
