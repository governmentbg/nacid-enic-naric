package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Cacheable(value = false)
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

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_last_update")
    private LocalDateTime dateLastUpdate;

    @Column(name = "user_last_update")
    private String userLastUpdate;

    @Column(name = "active")
    private Boolean active;

}
