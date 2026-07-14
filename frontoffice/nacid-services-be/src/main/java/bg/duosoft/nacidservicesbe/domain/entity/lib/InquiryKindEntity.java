package bg.duosoft.nacidservicesbe.domain.entity.lib;

import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationIdIndexIdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 28.02.2023
 * Time: 11:43
 */
@Entity
@Table(name = "lib_inquiry_kind", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class InquiryKindEntity implements Serializable {

    @EmbeddedId
    private ApplicationIdIndexIdEntity id;

    @ManyToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "apn_id", nullable = false)
    @MapsId("applicationId")
    private InquiryFullEntity inquiryApplication;

    @Column(name = "ink_code")
    private String inquiryKindCode;
}
