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
 * Date: 04.08.2023
 * Time: 15:22
 */
@Entity
@Table(name = "lib_public_access", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class PublicAccessFullEntity implements FullApplicationEntityBase {

    @Id
    @Column(name = "apn_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "apn_id")
    private ApplicationEntity application;

    @Column(name = "about")
    private String about;

    @Column(name = "comment")
    private String comment;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publicAccessApplication", orphanRemoval = true)
    private List<PublicAccessInfoFormEntity> details;
}
