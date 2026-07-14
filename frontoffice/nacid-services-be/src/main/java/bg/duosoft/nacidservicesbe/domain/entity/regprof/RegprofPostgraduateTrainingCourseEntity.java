package bg.duosoft.nacidservicesbe.domain.entity.regprof;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.GraduationDocTypeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.10.2022
 * Time: 12:33
 */
@Entity
@Table(name = "regprof_postgraduate_training_course", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class RegprofPostgraduateTrainingCourseEntity implements Serializable {

    @Id
    @Column(name = "rte_id")
    private Integer trainingCourseId;

    @OneToOne
    @JoinColumn(name = "rte_id", referencedColumnName = "rte_id")
    @MapsId
    private RegprofTrainingCourseEntity trainingCourse;

    @Column(name = "professional_institution_id")
    private Integer professionalInstitutionId;

    @Column(name = "professional_institution")
    private String professionalInstitution;

    @Column(name = "professional_institution_former_name_id")
    private Integer professionalInstitutionFormerNameId;

    @Column(name = "professional_institution_former_name")
    private String professionalInstitutionFormerName;

    @ManyToOne
    @JoinColumn(name = "graduation_document_type_id", referencedColumnName = "id")
    private GraduationDocTypeEntity graduationDocType;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "document_date")
    private LocalDate documentDate;

    @Column(name = "document_series")
    private String documentSeries;

    @Column(name = "document_reg_number")
    private String documentRegNumber;

    @Column(name = "professional_qualification")
    private String professionalQualification;
}
