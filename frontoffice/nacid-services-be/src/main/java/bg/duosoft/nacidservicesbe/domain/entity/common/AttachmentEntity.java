package bg.duosoft.nacidservicesbe.domain.entity.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.10.2022
 * Time: 16:55
 */
@Entity
@Table(name = "attachments", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class AttachmentEntity implements Serializable {

    @EmbeddedId
    private ApplicationIdIndexIdEntity id;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "apn_id", referencedColumnName = "apn_id"),
            @JoinColumn(name = "idx", referencedColumnName = "idx")
    })
    @MapsId
    private ApplicationAttachedDocEntity attachedDoc;

    @Column(name = "file_id")
    private String fileId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "relative_path")
    private String relativePath;

    @Column(name = "root_directory")
    private String rootDirectory;
}
