package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Cacheable(value = false)
@Table(name = "cfg_abdocs_action", schema = "nomenclatures")
public class CfgAbdocsActionEntity implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "abdocs_task_result")
    private String abdocsTaskResult;

    @Column(name = "abdocs_task_user")
    private String abdocsTaskUser;
}
