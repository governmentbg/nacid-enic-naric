package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CountryEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.TrainingCourseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * User: ggeorgiev
 * Date: 25.08.2022
 * Time: 17:59
 */
@Entity
@Table(name = "training_location", schema = "rudi")
@Data
public class TrainingLocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "country_code", referencedColumnName = "code")
    private CountryEntity country;

    @ManyToOne
    @JoinColumn(name = "tce_id", referencedColumnName = "id", nullable=false)
    private TrainingCourseEntity trainingCourse;

    @Column(name = "city")
    private String city;

    @ManyToOne
    @JoinTable(
            schema = "rudi",
            name = "training_location_examination_locations",
            joinColumns = {@JoinColumn(name = "training_location_id")},
            inverseJoinColumns = {@JoinColumn(name = "training_institution_id")})
    private TrainingInstitutionEntity examinationTrainingInstitution;

    //TODO:TrainingInstitution
}
