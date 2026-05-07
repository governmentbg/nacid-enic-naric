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
@Table(name = "user_entity", schema = "public")
@Proxy(lazy=false)
public class EUserEntity implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "email_constraint")
    private String emailConstraint;

    @Column(name = "email_verified")
    private Boolean emailVerified;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "federation_link")
    private String federationLink;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "realm_id")
    private String realmId;

    @Column(name = "username")
    private String username;

    @Column(name = "created_timestamp")
    private Long createdTimestamp;

    @Column(name = "service_account_client_link")
    private String serviceAccountClientLink;

    @Column(name = "not_before")
    private Integer notBefore;

}
