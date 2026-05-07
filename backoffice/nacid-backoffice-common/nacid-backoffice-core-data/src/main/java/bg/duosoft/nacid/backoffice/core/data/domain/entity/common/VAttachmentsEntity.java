package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "vw_attachments", schema = "common")
@Cacheable(value = false)
public class VAttachmentsEntity implements Serializable {

    @Id
    @Column(name = "attachment_id")
    private Integer attachmentId;

    @Column(name = "apn_id")
    private Integer applicationId;

    @Column(name = "doc_type_name")
    private String docTypeName;

    @Column(name = "direction")
    private String direction;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "bucket_name")
    private String bucketName;

    @Column(name = "file_location")
    private String fileLocation;

    @Column(name = "content_type")
    private String contentType;

}

