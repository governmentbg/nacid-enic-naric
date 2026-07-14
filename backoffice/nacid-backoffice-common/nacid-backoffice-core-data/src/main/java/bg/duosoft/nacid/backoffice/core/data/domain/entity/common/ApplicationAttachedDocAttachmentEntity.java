package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

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
@Table(name = "application_attached_doc_attachments", schema = "common")
@Cacheable(value = false)
public class ApplicationAttachedDocAttachmentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "application_attached_doc_id", referencedColumnName = "id")
    private ApplicationAttachedDocEntity attachedDoc;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    private AttachmentEntity attachment;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'ATTACHMENT_VISIBILITY'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "attachment_visibility", referencedColumnName = "code"))
    })
    private ReferenceDataEntity attachmentVisibility;

}
