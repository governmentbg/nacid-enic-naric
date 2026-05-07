package bg.duosoft.nacidservicesbe.domain.entity.lib;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.02.2023
 * Time: 16:12
 */
@Entity
@Table(name = "lib_official_note", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class OfficialNoteEntity implements Serializable {

    @Id
    @Column(name = "apn_id")
    private Integer id;
}
