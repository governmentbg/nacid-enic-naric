package bg.duosoft.nacidservicesbe.domain.entity.lib;

import bg.duosoft.nacidservicesbe.domain.entity.base.FullApplicationEntityBase;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.10.2022
 * Time: 17:40
 */
@Entity
@Table(name = "lib_suggestion", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class SuggestionFullEntity implements FullApplicationEntityBase {

    @Id
    @Column(name = "apn_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "apn_id")
    private ApplicationEntity application;

    @Column(name = "suggestion_text")
    private String suggestionText;
}
