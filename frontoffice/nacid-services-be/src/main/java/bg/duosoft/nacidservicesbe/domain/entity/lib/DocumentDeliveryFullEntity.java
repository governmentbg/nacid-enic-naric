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
 * Time: 17:21
 */
@Entity
@Table(name = "lib_document_delivery", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class DocumentDeliveryFullEntity implements FullApplicationEntityBase {

    @Id
    @Column(name = "apn_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "apn_id")
    private ApplicationEntity application;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documentDeliveryApplication", orphanRemoval = true)
    private List<DocumentDeliveryDetailsEntity> deliveryDetails;
}
