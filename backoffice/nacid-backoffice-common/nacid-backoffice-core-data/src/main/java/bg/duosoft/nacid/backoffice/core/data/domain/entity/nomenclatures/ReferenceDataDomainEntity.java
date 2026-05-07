package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

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
@Table(name = "reference_data_domain", schema = "nomenclatures")
public class ReferenceDataDomainEntity implements Serializable {
    @Id
    @Column(name = "domain", nullable = false)
    private String domain;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "fo_replication_flag", nullable = false)
    private Integer foReplicationFlag;

}