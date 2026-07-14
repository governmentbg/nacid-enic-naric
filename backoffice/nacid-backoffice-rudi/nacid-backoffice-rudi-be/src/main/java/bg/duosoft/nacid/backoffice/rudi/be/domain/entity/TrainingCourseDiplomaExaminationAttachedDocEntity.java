package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.AttachmentEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DocumentTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "training_course_diploma_examination_attached_docs", schema = "rudi")
public class TrainingCourseDiplomaExaminationAttachedDocEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "training_course_diploma_examination_id", referencedColumnName = "id")
    private TrainingCourseDiplomaExaminationEntity diplomaExamination;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "doc_type_id", referencedColumnName = "id")
    private DocumentTypeEntity documentType;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'COPY_TYPE'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "copy_type_code", referencedColumnName = "code"))
    })
    private ReferenceDataEntity copyType;

    @Column(name = "docflow_id")
    private String docflowId;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

}
