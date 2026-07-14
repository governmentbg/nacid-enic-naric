package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "vw_sdk_qualification", schema = "nomenclatures")
@Cacheable(value = false)
public class VSdkQualificationEntity implements Serializable {
    @Id
    @Column(name = "sdk_qualification")
    private String sdkQualification;
}
