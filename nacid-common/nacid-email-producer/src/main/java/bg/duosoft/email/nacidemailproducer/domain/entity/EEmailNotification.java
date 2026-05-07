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
@Table(name = "email_notification", schema = "emails")
@Cacheable(value = false)
public class EEmailNotification implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "text")
    private String text;

    @Column(name = "recipients")
    private String recipients;

    @Column(name = "reply_to")
    private String replyTo;

    @Column(name = "cc")
    private String cc;

    @Column(name = "bcc")
    private String bcc;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sent_date")
    private Date sentDate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "skip_sending")
    private Boolean skipSending;

    @Column(name = "is_html")
    private Boolean isHtml;
}
