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
 * Time: 17:22
 */
@Entity
@Table(name = "lib_official_note", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class OfficialNoteFullEntity implements FullApplicationEntityBase {

    @Id
    @Column(name = "apn_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "apn_id")
    private ApplicationEntity application;

    @Column(name = "detailed_information")
    private String detailedInformation;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "officialNoteApplication", orphanRemoval = true)
    private List<OfficialNoteDetailsEntity> officialNoteDetails;
}
