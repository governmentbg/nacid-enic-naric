package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VApplicationsByPersonEntityPK implements Serializable {
    @Column(name = "application_id")
    private Integer applicationId;

    @Column(name = "person_role")
    private String personRole;

    @Column(name = "person_id")
    private Integer personId;
}
