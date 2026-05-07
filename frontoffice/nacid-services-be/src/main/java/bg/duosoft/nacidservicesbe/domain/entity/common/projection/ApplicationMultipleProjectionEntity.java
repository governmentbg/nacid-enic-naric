package bg.duosoft.nacidservicesbe.domain.entity.common.projection;

import bg.duosoft.nacidservicesbe.domain.entity.common.MultipleApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.projection.BibliographicReferenceFullProjectionEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.projection.InquiryKindProjectionEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.projection.OfficialNoteDetailsProjectionEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.04.2023
 * Time: 14:12
 */
@Entity
@Table(name = "application", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class ApplicationMultipleProjectionEntity implements Serializable {

    @Id
    private Integer id;

    @Column(name = "ate_code")
    private String applicationTypeCode;

    @Column(name = "ase_code")
    private String applicationSubtypeCode;

    @Column(name = "temp_number")
    private String tempNumber;

    @Column(name = "entry_num")
    private String entryNumber;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    @ManyToOne
    @JoinColumn(name = "multiple_application_id")
    private MultipleApplicationEntity multipleApplication;

    @OneToMany
    @JoinColumn(name = "apn_id", referencedColumnName = "id")
    private List<InquiryKindProjectionEntity> inquiryKinds;

    @OneToMany
    @JoinColumn(name = "apn_id", referencedColumnName = "id")
    private List<OfficialNoteDetailsProjectionEntity> officialNotesDetails;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "apn_id")
    private BibliographicReferenceFullProjectionEntity biblioReference;
}
