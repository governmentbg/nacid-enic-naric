package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Cacheable(value = false)
@Table(name = "application_responsible_users", schema = "common")
public class ApplicationResponsibleUsersEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "id")
    private ApplicationEntity application;

    @Column(name = "responsible_user")
    private String responsibleUser;

    @Column(name = "date_from")
    private LocalDateTime dateFrom;

    @Column(name = "date_to")
    private LocalDateTime dateTo;
}
