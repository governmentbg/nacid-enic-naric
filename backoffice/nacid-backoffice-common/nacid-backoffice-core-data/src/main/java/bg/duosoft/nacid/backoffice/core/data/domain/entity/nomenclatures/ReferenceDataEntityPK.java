package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 14.07.2022
 * Time: 16:19
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ReferenceDataEntityPK implements Serializable {
    private static final long serialVersionUID = -411235218260062479L;
    @Column(name = "domain", nullable = false, length = 50)
    private String domain;
    @Column(name = "code", nullable = false, length = 20)
    private String id;
}
