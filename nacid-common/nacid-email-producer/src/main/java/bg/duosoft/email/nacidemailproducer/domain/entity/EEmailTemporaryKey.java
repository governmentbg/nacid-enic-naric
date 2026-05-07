package bg.duosoft.email.nacidemailproducer.domain.entity;

import bg.duosoft.email.nacidemailproducer.enums.EmailTemporaryKeyType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "email_temporary_key", schema = "emails")
@Cacheable(value = false)
public class EEmailTemporaryKey implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EmailTemporaryKeyType type;

    @Column(name = "key")
    private String key;

    @Column(name = "username")
    private String user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiration_date")
    private Date expirationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "used_on_date")
    private Date usedOnDate;

    @Column(name = "extra_data")
    private String extraData;

}
