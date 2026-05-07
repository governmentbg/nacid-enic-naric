package bg.duosoft.nacidservicesbe.domain.entity.lib;

import bg.duosoft.nacidservicesbe.domain.entity.base.FullApplicationEntityBase;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.10.2022
 * Time: 17:38
 */
@Entity
@Table(name = "lib_signal", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class SignalFullEntity implements FullApplicationEntityBase {

    @Id
    @Column(name = "apn_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "apn_id")
    private ApplicationEntity application;

    @Column(name = "violation_text")
    private String violationText;

    @Column(name = "violation_place")
    private String violationPlace;

    @Column(name = "checktime_text")
    private String checktimeText;

    @Column(name = "damage_text")
    private String damageText;

    @Column(name = "actions_text")
    private String actionsText;
}
