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
@Cacheable(value = false)
@Table(name = "cfg_abdocs_document", schema = "nomenclatures")
public class CfgAbdocsDocumentEntity implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "doc_type_id")
    private Integer docTypeId;

    @Column(name = "doc_reg_type_id")
    private Integer docRegistrationTypeId;

    @Column(name = "doc_subject")
    private String docSubject;

    @Column(name = "doc_to")
    private String docTo;
}
