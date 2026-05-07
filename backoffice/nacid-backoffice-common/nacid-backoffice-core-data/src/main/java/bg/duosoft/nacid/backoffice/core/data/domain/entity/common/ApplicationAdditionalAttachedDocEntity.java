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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_additional_attached_docs", schema = "common")
@Cacheable(value = false)
public class ApplicationAdditionalAttachedDocEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "submission_id")
    private ApplicationAdditionalSubmissionEntity submission;

    @ManyToOne
    @JoinColumn(name = "doc_type_id", referencedColumnName = "id")
    private DocumentTypeEntity documentType;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'COPY_TYPE'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "copy_type_code", referencedColumnName = "code"))
    })
    private ReferenceDataEntity copyType;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    private AttachmentEntity attachment;
}
