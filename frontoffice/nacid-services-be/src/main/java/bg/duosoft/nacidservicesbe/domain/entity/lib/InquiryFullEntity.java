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
@Table(name = "lib_inquiry", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class InquiryFullEntity implements FullApplicationEntityBase {

    @Id
    @Column(name = "apn_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "apn_id")
    private ApplicationEntity application;

    @Column(name = "inquiry_aim")
    private String inquiryAim;

    @Column(name = "period_from")
    private Integer periodFrom;

    @Column(name = "period_to")
    private Integer periodTo;

    @Column(name = "previous_inquiry")
    private String previousInquiryNum;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inquiryApplication", orphanRemoval = true)
    private List<InquiryKindEntity> inquiryKinds;
}
