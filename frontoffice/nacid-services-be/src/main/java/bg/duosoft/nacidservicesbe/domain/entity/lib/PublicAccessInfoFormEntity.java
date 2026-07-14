package bg.duosoft.nacidservicesbe.domain.entity.lib;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationIdIndexIdEntity;
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
 * Time: 17:07
 */
@Entity
@Table(name = "lib_public_access_info_form", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class PublicAccessInfoFormEntity implements Serializable {

    @EmbeddedId
    private ApplicationIdIndexIdEntity id;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'PUBLIC_ACCESS_INFO_FORM'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "pif_code", referencedColumnName="code"))
    })
    private ReferenceDataEntity infoForm;

    @ManyToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "apn_id", nullable = false)
    @MapsId("applicationId")
    private PublicAccessFullEntity publicAccessApplication;
}
