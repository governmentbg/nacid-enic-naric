package bg.duosoft.nacidservicesbe.domain.entity.regprof;

import bg.duosoft.nacidservicesbe.domain.entity.base.FullApplicationEntityBase;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.10.2022
 * Time: 17:47
 */
@Entity
@Table(name = "regprof_application", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class RegprofApplicationFullEntity implements FullApplicationEntityBase {

    @Id
    @Column(name = "apn_id")
    private Integer id;

    @Column(name = "apostille_application_flag")
    private Integer apostilleApplicationFlag;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn(name = "apn_id")
    private ApplicationEntity application;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "apn_id", referencedColumnName = "apn_id", updatable = false, insertable = false, unique = true)
    private List<RegprofTrainingExperienceEntity> trainingAndExperience;


}
