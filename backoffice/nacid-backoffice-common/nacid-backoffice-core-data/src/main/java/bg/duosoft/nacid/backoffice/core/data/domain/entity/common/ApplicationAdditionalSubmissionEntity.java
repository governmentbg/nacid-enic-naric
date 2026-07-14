package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_additional_submissions", schema = "common")
@Cacheable(value = false)
public class ApplicationAdditionalSubmissionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "id")
    private ApplicationEntity application;

    @Column(name = "entry_num")
    private String entryNum;

    @Column(name = "entry_date", nullable = false)
    private LocalDate entryDate;

    @Column(name = "description")
    private String description;

    @Column(name = "submission_date")
    private LocalDateTime submissionDate;

    @Column(name = "bo_user_accepted")
    private String boUserAccepted;

    @Column(name = "bo_date_transferred")
    private LocalDateTime boDateTransferred;

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationAdditionalAttachedDocEntity> attachedDocs;
}
