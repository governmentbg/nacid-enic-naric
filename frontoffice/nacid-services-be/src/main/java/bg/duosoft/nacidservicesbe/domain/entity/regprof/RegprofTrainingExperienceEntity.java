package bg.duosoft.nacidservicesbe.domain.entity.regprof;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CountryEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.10.2022
 * Time: 11:04
 */
@Entity
@Table(name = "regprof_training_experience", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class RegprofTrainingExperienceEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "apn_id", updatable = false)
    private Integer regprofApplicationId;

    @Column(name = "certificate_prof_qualification")
    private String certificateProfQualification;

    @Column(name = "not_restricted_flag")
    private Integer notRestrictedFlag;

    @ManyToOne
    @JoinColumn(name = "applies_for_country", referencedColumnName = "code")
    private CountryEntity appliesForCountry;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "trainingExperience", orphanRemoval = true)
    private RegprofTrainingCourseEntity trainingCourse;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "trainingExperience", orphanRemoval = true)
    private RegprofExperienceEntity experience;
}
