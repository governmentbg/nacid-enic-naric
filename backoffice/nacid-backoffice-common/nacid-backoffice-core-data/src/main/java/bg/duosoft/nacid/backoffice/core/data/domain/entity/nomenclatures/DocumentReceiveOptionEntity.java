package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "document_receive_option", schema = "nomenclatures")
@EqualsAndHashCode
public class DocumentReceiveOptionEntity implements Serializable, NomenclatureEntityBase<String> {
    @Id
    @Column(name = "code", nullable = false, length = 4)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private Integer active;

    @Column(name = "document_recipient_flag")
    private Integer documentRecipient;

    @Column(name = "index")
    private Integer index;

    @ManyToOne
    @JoinColumn(name = "option_kind_code")
    private DocumentReceiveOptionKindEntity optionKind;
}
