package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.09.2022
 * Time: 12:56
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "reference_data_domain", schema = "nomenclatures")
public class ReferenceDataDomainEntity implements Serializable {

    @Id
    @Column(name = "domain")
    private String domain;

    @Column(name = "name")
    private String name;

    @Column(name = "fo_only")
    private Integer foOnly;
}
