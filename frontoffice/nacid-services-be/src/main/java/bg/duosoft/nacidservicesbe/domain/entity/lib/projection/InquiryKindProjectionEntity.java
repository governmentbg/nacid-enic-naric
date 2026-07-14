package bg.duosoft.nacidservicesbe.domain.entity.lib.projection;

import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationIdIndexIdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 12.04.2023
 * Time: 12:02
 */
@Entity
@Table(name = "lib_inquiry_kind", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class InquiryKindProjectionEntity {

    @EmbeddedId
    private ApplicationIdIndexIdEntity id;

    @Column(name = "ink_code")
    private String inquiryKindCode;
}
