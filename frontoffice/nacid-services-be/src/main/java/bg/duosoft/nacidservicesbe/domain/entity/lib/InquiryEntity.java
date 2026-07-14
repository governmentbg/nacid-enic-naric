package bg.duosoft.nacidservicesbe.domain.entity.lib;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 28.02.2023
 * Time: 11:48
 */
@Entity
@Table(name = "lib_inquiry", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class InquiryEntity implements Serializable {

    @Id
    @Column(name = "apn_id")
    private Integer id;
}
