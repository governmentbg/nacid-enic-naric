package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "reference_data", schema = "nomenclatures")
@EqualsAndHashCode
public class ReferenceDataEntity implements Serializable {
    @EmbeddedId
    private ReferenceDataEntityPK pk;

    public ReferenceDataEntity(ReferenceDataEntityPK pk) {
        this.pk = pk;
    }

    @OneToOne
    @JoinColumn(name = "domain", insertable = false, updatable = false)
    private ReferenceDataDomainEntity referenceDataDomain;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "index", nullable = false)
    private Integer index;


    @Column(name = "active", nullable = false)
    private Integer active;
}
