package bg.duosoft.nacidservicesbe.domain.entity.lib;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 28.02.2023
 * Time: 17:55
 */
@Entity
@Table(name = "lib_bibliographic_reference", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class BibliographicReferenceEntity implements Serializable {

    @Id
    @Column(name = "apn_id")
    private Integer id;
}
