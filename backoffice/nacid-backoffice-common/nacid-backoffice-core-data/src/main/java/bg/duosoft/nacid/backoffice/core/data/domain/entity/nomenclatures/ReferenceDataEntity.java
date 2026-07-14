package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 14.07.2022
 * Time: 16:09
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Cacheable(value = false)
@Table(name = "reference_data", schema = "nomenclatures")
public class ReferenceDataEntity implements Serializable {
    @EmbeddedId
    private ReferenceDataEntityPK pk;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "index", nullable = false)
    private Integer index;

    @Column(name = "active", nullable = false)
    private Integer active;

    @OneToOne
    @JoinColumn(name = "domain", insertable = false, updatable = false)
    private ReferenceDataDomainEntity referenceDataDomain;

    public ReferenceDataEntity(ReferenceDataDomain domain, String id) {
        this.pk = new ReferenceDataEntityPK(domain.domain(), id);
    }
}
