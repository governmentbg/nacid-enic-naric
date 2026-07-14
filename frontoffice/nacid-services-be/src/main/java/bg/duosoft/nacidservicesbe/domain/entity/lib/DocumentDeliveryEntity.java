package bg.duosoft.nacidservicesbe.domain.entity.lib;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.03.2023
 * Time: 17:32
 */
@Entity
@Table(name = "lib_document_delivery", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class DocumentDeliveryEntity implements Serializable {

    @Id
    @Column(name = "apn_id")
    private Integer id;
}
