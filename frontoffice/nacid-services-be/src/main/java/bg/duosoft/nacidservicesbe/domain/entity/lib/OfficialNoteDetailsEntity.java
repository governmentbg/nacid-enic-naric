package bg.duosoft.nacidservicesbe.domain.entity.lib;

import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationIdIndexIdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.02.2023
 * Time: 14:30
 */
@Entity
@Table(name = "lib_official_note_details", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class OfficialNoteDetailsEntity implements Serializable {

    @EmbeddedId
    private ApplicationIdIndexIdEntity id;

    @ManyToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "apn_id", nullable = false)
    @MapsId("applicationId")
    private OfficialNoteFullEntity officialNoteApplication;

    @Column(name = "onk_code")
    private String officialNoteKindCode;
}
