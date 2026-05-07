package bg.duosoft.nacidservicesbe.domain.entity.lib;

import bg.duosoft.nacidservicesbe.domain.entity.base.FullApplicationEntityBase;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.10.2022
 * Time: 17:14
 */
@Entity
@Table(name = "lib_bibliographic_reference", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class BibliographicReferenceFullEntity implements FullApplicationEntityBase {

    @Id
    @Column(name = "apn_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "apn_id")
    private ApplicationEntity application;

    @Column(name = "search_bg_flag")
    private Integer searchBgFlag;

    @Column(name = "search_foreign_flag")
    private Integer searchForeignFlag;

    @Column(name = "result_kind_bg")
    private String resultKindCodeBg;

    @Column(name = "result_kind_foreign")
    private String resultKindCodeForeign;

    @Column(name = "subject")
    private String subject;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "period_ret_from")
    private Integer periodFrom;

    @Column(name = "period_ret_to")
    private Integer periodTo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bibliographicReferenceApplication", orphanRemoval = true)
    private List<BibliographicReferenceLanguageEntity> languages;
}
