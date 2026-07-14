package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vw_commission_applications_list", schema = "rudi")
@Cacheable(value = false)
public class VRudiCommissionApplicationsEntity extends VRudiApplicationsEntityBase implements Serializable {
    @EmbeddedId
    private VRudiCommissionApplicationsEntityPK pk;

    @Column(name = "motives")
    private String motives;

    @Column(name = "applicant_info")
    private String applicantInfo;

    @Column(name = "generated_final_doc")
    private Integer generatedFinalDoc;

    @Column(name = "abdocs_transferred")
    private Integer abdocsTransferred;
}
