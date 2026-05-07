package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Cacheable(value = false)
@Table(name = "commission_member_position", schema = "nomenclatures")
public class CommissionMemberPositionEntity implements Serializable, StringKeyNomenclatureEntityBase {
    public CommissionMemberPositionEntity(String id) {
        this.id = id;
    }

    @Id
    @Column(name = "code", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private Integer active;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'APPLICATION_STATUS'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "related_app_status_code", referencedColumnName="code"))
    })
    private ReferenceDataEntity applicationStatus;

}