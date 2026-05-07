package bg.duosoft.nacidservicesbe.domain.entity.rudi;

import bg.duosoft.nacidservicesbe.domain.entity.base.FullApplicationEntityBase;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.10.2022
 * Time: 13:06
 */
@Entity
@Table(name = "rudi_application", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class RudiApplicationFullEntity implements FullApplicationEntityBase {

    @Id
    @Column(name = "apn_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn(name = "apn_id")
    private ApplicationEntity application;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "apn_id", referencedColumnName = "apn_id", updatable = false, insertable = false)
    private RudiSarApplicationEntity sarApplication;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "apn_id", referencedColumnName = "apn_id", updatable = false, insertable = false, unique = true)
    private List<RudiTrainingCourseEntity> trainingCourses;
}
