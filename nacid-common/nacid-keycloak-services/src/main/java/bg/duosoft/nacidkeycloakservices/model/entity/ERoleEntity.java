package bg.duosoft.nacidkeycloakservices.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "keycloak_role", schema = "public")
@Proxy(lazy=false)
public class ERoleEntity implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "client_realm_constraint")
    private String clientRealmConstraint;

    @Column(name = "client_role")
    private Boolean clientRole;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "realm_id")
    private String realmId;

    @Column(name = "client")
    private String client;

    @Column(name = "realm")
    private String realm;

}
