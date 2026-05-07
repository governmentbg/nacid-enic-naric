package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_certificates", schema = "common")
@Cacheable(value = false)
public class ApplicationCertificatesEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "id")
    private ApplicationEntity application;

    @Column(name = "certificate_number")
    private String certificateNumber;

    @Column(name = "uuid", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String uuid;

    @Column(name = "certificate_status")
    private String certificateStatus;

    @Column(name = "application_attached_doc_id")
    private Integer applicationAttachedDocId;

}
