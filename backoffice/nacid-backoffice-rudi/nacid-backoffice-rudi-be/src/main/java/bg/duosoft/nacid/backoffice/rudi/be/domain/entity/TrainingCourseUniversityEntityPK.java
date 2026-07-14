package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrainingCourseUniversityEntityPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "tce_id", referencedColumnName = "id")
    private TrainingCourseEntity trainingCourse;

    @ManyToOne
    @JoinColumn(name = "uny_id", referencedColumnName = "id")
    private UniversityEntity university;

}
