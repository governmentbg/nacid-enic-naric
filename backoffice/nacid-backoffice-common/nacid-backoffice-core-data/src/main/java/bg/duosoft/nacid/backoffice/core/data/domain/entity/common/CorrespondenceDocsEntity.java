package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Cacheable(value = false)
@Table(name = "correspondence_docs", schema = "common")
public class CorrespondenceDocsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "attached_doc_id", referencedColumnName = "id")
    private ApplicationAttachedDocEntity applicationAttachedDoc;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "fo_send_date")
    private LocalDateTime foSendDate;

    @Column(name = "fo_read_date")
    private LocalDateTime foReadDate;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

}
