package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "personal_nacid_id", schema = "common")
@Cacheable(value = false)
public class PersonalNacidIdEntity implements Serializable {
    @Id
    @Column(name = "value")
    private String value;

    @Column(name = "user_generated")
    private String userGenerated;

    @Column(name = "date_generated")
    private LocalDateTime dateGenerated;
}
