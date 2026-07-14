package bg.duosoft.nacidservicesbe.domain.entity.rudi;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CountryEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.10.2022
 * Time: 15:31
 */
@Entity
@Table(name = "rudi_training_location", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class RudiTrainingLocationEntity implements Serializable, RudiTrainingCourseRelated {

    @EmbeddedId
    private RudiTrainingCourseIndexIdEntity id;

    @ManyToOne
    @JoinColumn(name = "tce_id", referencedColumnName = "id", nullable = false)
    @MapsId("trainingCourseId")
    private RudiTrainingCourseEntity trainingCourse;

    @ManyToOne
    @JoinColumn(name = "country_code", referencedColumnName = "code")
    private CountryEntity country;

    @Column(name = "city")
    private String city;
}
