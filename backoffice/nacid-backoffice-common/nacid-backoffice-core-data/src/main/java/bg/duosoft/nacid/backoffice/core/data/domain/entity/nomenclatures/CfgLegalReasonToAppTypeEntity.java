package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 27.06.2023
 */
@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "cfg_legal_reason_to_app_type", schema = "nomenclatures")
public class CfgLegalReasonToAppTypeEntity implements Serializable {
    @EmbeddedId
    private CfgLegalReasonToAppTypeEntityPK pk;

    @ManyToOne
    @JoinColumn(name = "ate_code", referencedColumnName="code", updatable = false, insertable = false)
    private ApplicationTypeEntity applicationType;

    @ManyToOne
    @JoinColumn(name = "ase_code", referencedColumnName="code", updatable = false, insertable = false)
    private ApplicationSubtypeEntity applicationSubtype;

    @ManyToOne
    @MapsId(value = "lrnId")
    @JoinColumn(name = "lrn_id", referencedColumnName="id", updatable = false, insertable = false)
    private LegalReasonEntity legalReason;

}
