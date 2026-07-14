package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

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
public class VRudiCommissionApplicationsEntityPK implements Serializable {

    @Column(name = "id")
    private Integer id;

    @Column(name = "calendar_id")
    private Integer calendarId;

}
