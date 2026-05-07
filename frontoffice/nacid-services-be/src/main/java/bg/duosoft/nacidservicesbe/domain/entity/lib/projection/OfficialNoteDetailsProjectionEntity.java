package bg.duosoft.nacidservicesbe.domain.entity.lib.projection;

import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationIdIndexIdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 12.04.2023
 * Time: 12:06
 */
@Entity
@Table(name = "lib_official_note_details", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class OfficialNoteDetailsProjectionEntity {

    @EmbeddedId
    private ApplicationIdIndexIdEntity id;

    @Column(name = "onk_code")
    private String officialNoteKindCode;
}
