package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Cacheable(value = false)
@Table(name = "application_notes", schema = "common")
public class ApplicationNotesEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "id")
    private ApplicationEntity application;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "created_user")
    private String createdUser;

    @Column(name = "note")
    private String note;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationNotesEntity that = (ApplicationNotesEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(application, that.application) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdUser, that.createdUser) &&
                Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, application, createdDate, createdUser, note);
    }

}
