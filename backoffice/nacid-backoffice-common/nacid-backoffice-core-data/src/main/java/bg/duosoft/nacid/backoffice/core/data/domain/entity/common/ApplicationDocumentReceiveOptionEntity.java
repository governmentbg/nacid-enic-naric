package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DocumentReceiveOptionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DocumentReceiveOptionKindEntity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.05.2024
 * Time: 13:10
 */
@Entity
@Table(name = "application_document_receive_option", schema = "common")
@Getter
@Setter
@Cacheable(false)
public class ApplicationDocumentReceiveOptionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "id")
    private ApplicationEntity application;

    @ManyToOne
    @JoinColumn(name = "document_receive_option_code")
    private DocumentReceiveOptionEntity documentReceiveOption;

    @ManyToOne
    @JoinColumn(name = "document_recipient_address", referencedColumnName = "id")
    private AddressEntity documentRecipientAddress;

    @ManyToOne
    @JoinColumn(name = "option_kind_code")
    private DocumentReceiveOptionKindEntity optionKind;
}
