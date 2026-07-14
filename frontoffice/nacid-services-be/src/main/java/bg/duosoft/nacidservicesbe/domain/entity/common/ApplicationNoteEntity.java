package bg.duosoft.nacidservicesbe.domain.entity.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 14.08.2023
 * Time: 13:58
 */
@Entity
@Table(name = "application_notes", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class ApplicationNoteEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "apn_id")
    private Integer applicationId;

    @Column(name = "note_text")
    private String noteText;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "user_created")
    private String userCreated;

    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;

    @Column(name = "user_updated")
    private String userUpdated;
}
