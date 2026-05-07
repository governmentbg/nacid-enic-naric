package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 02.09.2022
 * Time: 17:57
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Cacheable(value = false)
@Table(name = "cfg_graduation_document_type_config", schema = "nomenclatures")
public class CfgGraduationDocumentTypeConfigEntity implements Serializable {

    @EmbeddedId
    private CfgGraduationDocumentTypeConfigPK pk;

    @ManyToOne
    @JoinColumn(name = "country_code", referencedColumnName = "code", insertable = false,updatable = false)
    private CountryEntity country;

    @ManyToOne
    @MapsId(value = "graduationDocumentTypeId")
    @JoinColumn(name = "graduation_document_type_id", referencedColumnName = "id", insertable = false,updatable = false)
    private GraduationDocumentTypeEntity graduationDocumentType;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'EDUCATION_TYPE'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "education_type", referencedColumnName = "code", insertable = false,updatable = false))
    })
    private ReferenceDataEntity educationType;
}
