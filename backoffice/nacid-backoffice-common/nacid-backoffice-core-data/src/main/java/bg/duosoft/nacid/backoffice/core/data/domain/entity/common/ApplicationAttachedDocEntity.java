package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

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
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_attached_docs", schema = "common")
@Cacheable(value = false)
public class ApplicationAttachedDocEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "id")
    private ApplicationEntity application;

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

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'DOC_CATEGORY'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "doc_category", referencedColumnName = "code"))
    })
    private ReferenceDataEntity docCategory;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @OneToMany
    @JoinColumn(name = "application_attached_doc_id", referencedColumnName = "id", insertable = false, updatable = false)
    private List<ApplicationCertificatesEntity> applicationCertificates;

    @OneToMany(mappedBy = "attachedDoc", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationAttachedDocAttachmentEntity> attachedDocAttachments;

}
