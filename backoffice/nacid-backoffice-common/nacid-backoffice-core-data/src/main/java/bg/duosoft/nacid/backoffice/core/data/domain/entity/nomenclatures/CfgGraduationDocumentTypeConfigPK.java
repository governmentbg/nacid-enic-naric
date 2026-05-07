package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 02.09.2022
 * Time: 17:58
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CfgGraduationDocumentTypeConfigPK implements Serializable {
    private static final long serialVersionUID = -411235218260062479L;
    @Column(name = "graduation_document_type_id", nullable = false)
    private Integer graduationDocumentTypeId;
    @Column(name = "country_code", nullable = false)
    private String countryCode;
    @Column(name = "education_type", nullable = false)
    private String educationType;
}