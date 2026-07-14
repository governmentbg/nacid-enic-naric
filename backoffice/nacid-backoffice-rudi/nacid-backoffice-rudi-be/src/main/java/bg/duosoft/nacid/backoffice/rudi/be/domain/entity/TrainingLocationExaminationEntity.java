package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "training_location_examination", schema = "rudi")
public class TrainingLocationExaminationEntity implements Serializable {
    @Id
    @Column(name = "tce_id")
    private Integer id;

    @Column(name = "legitimate_flag")
    private Integer legitimateFlag;

    @OneToOne
    @MapsId
    @JoinColumn(name = "tce_id", referencedColumnName = "id")
    private TrainingCourseEntity trainingCourse;
}
