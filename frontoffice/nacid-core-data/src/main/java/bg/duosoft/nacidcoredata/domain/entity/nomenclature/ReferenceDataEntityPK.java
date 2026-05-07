package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ReferenceDataEntityPK implements Serializable {
    private static final long serialVersionUID = -411235218260062480L;
    @Column(name = "domain", nullable = false, length = 50)
    private String domain;
    @Column(name = "code", nullable = false, length = 20)
    private String id;
}
