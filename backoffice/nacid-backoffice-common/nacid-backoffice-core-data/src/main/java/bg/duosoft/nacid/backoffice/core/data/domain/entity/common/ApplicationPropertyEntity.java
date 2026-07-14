package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 14.09.2022
 * Time: 17:34
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Cacheable(value = false)
@Table(name = "application_properties", schema = "common")
public class ApplicationPropertyEntity implements Serializable  {
    @Id
    @Column(name = "code")
    private String id;

    @Column(name = "value")
    private String value;

    @Column(name = "description")
    private String description;
}
