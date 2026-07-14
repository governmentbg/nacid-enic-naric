package bg.duosoft.nacidservicesbe.domain.entity.common;

import bg.duosoft.nacidservicesbe.domain.entity.common.projection.ApplicationMultipleProjectionEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.04.2023
 * Time: 12:16
 */
@Entity
@Table(name = "multiple_application", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class MultipleApplicationEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "multipleApplication", cascade = {})
    private List<ApplicationMultipleProjectionEntity> applications;
}
