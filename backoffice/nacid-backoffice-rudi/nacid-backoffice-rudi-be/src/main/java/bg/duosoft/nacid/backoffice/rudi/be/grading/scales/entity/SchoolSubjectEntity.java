package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "school_subject", schema = "secondary")
public class SchoolSubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "subject_bg")
    private String subjectBg;

    @Column(name = "subject_en")
    private String subjectEn;
}
