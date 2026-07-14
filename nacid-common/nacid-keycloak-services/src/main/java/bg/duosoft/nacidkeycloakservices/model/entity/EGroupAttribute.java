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

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.07.2022
 * Time: 14:09
 */
@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "group_attribute", schema = "public")
@Proxy(lazy=false)
public class EGroupAttribute implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @Column(name = "group_id")
    private String groupId;
}
