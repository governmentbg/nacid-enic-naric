package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.base.NomenclatureEntityBase;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.04.2023
 * Time: 16:22
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "application_subtype", schema = "nomenclatures")
public class ApplicationSubtypeEntity implements Serializable, NomenclatureEntityBase<String> {

    @Id
    @Column(name = "code")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "ate_code")
    private String applicationTypeCode;

    @Column(name = "active")
    private Integer active;
}
