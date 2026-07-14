package bg.duosoft.nacidservicesbe.domain.entity.common;

import bg.duosoft.nacidservicesbe.domain.entity.common.projection.ApplicationProjectionEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.09.2023
 * Time: 18:16
 */
@Entity
@Table(name = "application_correspondence", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class ApplicationCorrespondenceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "apn_id")
    private Integer applicationId;

    @ManyToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "id", updatable = false, insertable = false)
    private ApplicationProjectionEntity application;

    @Column(name = "bo_attached_doc_id")
    private Integer boAttachedDocId;

    @Column(name = "about")
    private String about;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_read")
    private LocalDateTime dateRead;

}
