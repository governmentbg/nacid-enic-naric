package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "subjects_grades_statistic", schema = "secondary")
public class SubjectsGradesStatisticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "original_grade")
    private String originalGrade;

    @Column(name = "equated_grade")
    private Double equatedGrade;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
