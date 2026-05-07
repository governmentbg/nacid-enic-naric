package bg.duosoft.email.nacidemailproducer.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "email_template", schema = "emails")
@Cacheable(value = false)
public class EEmailTemplate implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "subject")
    private String subject;

    @Column(name = "text")
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "is_html")
    private Boolean isHtml;

    @Column(name = "user_create")
    private String userCreate;

    @Column(name = "user_last_update")
    private String userLastUpdate;

    @Column(name = "params")
    private String params;

}
