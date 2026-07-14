package bg.duosoft.nacidservicesbe.domain.entity.common;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.DocTypeEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.10.2022
 * Time: 16:58
 */
@Entity
@Table(name = "application_attached_docs", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class ApplicationAttachedDocEntity implements Serializable {

    @EmbeddedId
    private ApplicationIdIndexIdEntity id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "doc_type_id", referencedColumnName = "id")
    private DocTypeEntity docType;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'COPY_TYPE'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "copy_type_code", referencedColumnName="code"))
    })
    private ReferenceDataEntity copyType;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "attachedDoc", orphanRemoval = true)
    private AttachmentEntity attachment;
}
