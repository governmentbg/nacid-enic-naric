package bg.duosoft.nacidcoredata.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "content_management", schema = "common")
public class ContentManagementEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "data_template")
    private String dataTemplate;

    @Column(name = "type")
    private String type;

    @Column(name = "data")
    private String data;

    @Column(name = "content_order")
    private Integer contentOrder;

    @Column(name = "alias")
    private String alias;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_created")
    private Date dateCreated;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_last_update")
    private Date dateLastUpdate;

    @Column(name = "user_last_update")
    private String userLastUpdate;

    @Column(name = "active")
    private Boolean active;

}
