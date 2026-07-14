package bg.duosoft.nacidservicesbe.domain.entity.common.projection;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ApplicationSubtypeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.10.2023
 * Time: 11:41
 */
@Entity
@Table(name = "application", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class ApplicationProjectionEntity  implements Serializable {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ase_code")
    private ApplicationSubtypeEntity applicationSubtype;

    @Column(name = "entry_num")
    private String entryNumber;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    @Column(name = "temp_number")
    private String tempNumber;

    @Column(name = "user_created")
    private String userCreated;
}
