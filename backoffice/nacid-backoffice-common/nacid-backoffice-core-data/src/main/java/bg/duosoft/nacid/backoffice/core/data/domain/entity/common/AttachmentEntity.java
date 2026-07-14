package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Cacheable(value = false)
@Table(name = "attachments", schema = "common")
public class AttachmentEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "file_size")
    private Integer fileSize;

    @Column(name = "file_location")
    private String fileLocation;

    @Column(name = "bucket_name")
    private String bucketName;

}
