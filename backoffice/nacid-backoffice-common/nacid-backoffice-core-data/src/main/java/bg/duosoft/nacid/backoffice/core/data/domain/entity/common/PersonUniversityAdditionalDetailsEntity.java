package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Cacheable(value = false)
@Table(name = "person_university_additional_details", schema = "common")
public class PersonUniversityAdditionalDetailsEntity implements Serializable {

    @Id
    @Column(name = "person_id")
    private Integer universityId;

    @Column(name = "letter_recipient")
    private String letterRecipient;

    @Column(name = "letter_greeting")
    private String letterGreeting;

}
