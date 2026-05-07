package bg.duosoft.nacidservicesbe.domain.entity.regprof;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ProfExperienceDocTypeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.10.2022
 * Time: 11:53
 */
@Entity
@Table(name = "regprof_profession_experience_documents", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class RegprofExperienceDocumentEntity implements Serializable {

    @EmbeddedId
    private RegprofExperienceDocumentIdEntity id;

    @ManyToOne
    @JoinColumn(name = "rte_id", referencedColumnName = "rte_id", nullable = false)
    @MapsId("experienceId")
    private RegprofExperienceEntity experience;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "document_issuer")
    private String documentIssuer;

    @Column(name = "document_date")
    private LocalDate documentDate;

    @ManyToOne
    @JoinColumn(name = "profession_experience_document_type_code", referencedColumnName = "code")
    private ProfExperienceDocTypeEntity documentType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experienceDocument", orphanRemoval = true)
    private List<RegprofExperienceDocumentDateEntity> documentDates;
}
