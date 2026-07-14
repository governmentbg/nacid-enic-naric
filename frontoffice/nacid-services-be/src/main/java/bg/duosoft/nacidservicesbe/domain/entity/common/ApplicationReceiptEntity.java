package bg.duosoft.nacidservicesbe.domain.entity.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.01.2023
 * Time: 18:20
 */
@Entity
@Table(name = "application_receipts", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class ApplicationReceiptEntity implements Serializable {

    @EmbeddedId
    private ApplicationIdIndexIdEntity id;

    @Column(name = "file_id")
    private String fileId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "relative_path")
    private String relativePath;

    @Column(name = "root_directory")
    private String rootDirectory;

    @Column(name = "status_code")
    private String statusCode;

    @Column(name = "active")
    private Integer active;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

}
