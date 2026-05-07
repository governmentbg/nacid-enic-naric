package bg.duosoft.nacidservicesbe.domain.entity.regprof;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.10.2022
 * Time: 11:48
 */
@Entity
@Table(name = "regprof_profession_experience", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class RegprofExperienceEntity implements Serializable {

    @Id
    @Column(name = "rte_id")
    private Integer trainingExperienceId;

    @OneToOne
    @JoinColumn(name = "rte_id", referencedColumnName = "id")
    @MapsId
    private RegprofTrainingExperienceEntity trainingExperience;

    @Column(name = "profession_name")
    private String professionName;

    @Column(name = "years")
    private Integer years;

    @Column(name = "months")
    private Integer months;

    @Column(name = "days")
    private Integer days;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experience", orphanRemoval = true)
    private List<RegprofExperienceDocumentEntity> documents;

}
