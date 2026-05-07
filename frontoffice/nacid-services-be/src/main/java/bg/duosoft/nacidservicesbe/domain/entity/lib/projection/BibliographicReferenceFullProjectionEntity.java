package bg.duosoft.nacidservicesbe.domain.entity.lib.projection;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 12.04.2023
 * Time: 12:07
 */
@Entity
@Table(name = "lib_bibliographic_reference", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class BibliographicReferenceFullProjectionEntity {

    @Id
    @Column(name = "apn_id")
    private Integer id;

    @Column(name = "search_bg_flag")
    private Integer searchBgFlag;

    @Column(name = "search_foreign_flag")
    private Integer searchForeignFlag;
}

