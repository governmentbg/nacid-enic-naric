package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vw_applications_list", schema = "rudi")
@Cacheable(value = false)
public class VRudiApplicationsEntity extends VRudiApplicationsEntityBase implements Serializable {
    @Id
    private Integer id;

    @Column(name = "backoffice_date")
    private LocalDate backofficeDate;
}
