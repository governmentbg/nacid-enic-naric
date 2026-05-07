package bg.duosoft.nacidservicesbe.domain.entity.lib;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.LanguageEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationIdIndexIdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.10.2022
 * Time: 17:07
 */
@Entity
@Table(name = "lib_bibliographic_reference_language", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class BibliographicReferenceLanguageEntity implements Serializable {

    @EmbeddedId
    private ApplicationIdIndexIdEntity id;

    @ManyToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "apn_Id", nullable = false)
    @MapsId("applicationId")
    private BibliographicReferenceFullEntity bibliographicReferenceApplication;

    @ManyToOne
    @JoinColumn(name = "lae_code")
    private LanguageEntity language;
}
