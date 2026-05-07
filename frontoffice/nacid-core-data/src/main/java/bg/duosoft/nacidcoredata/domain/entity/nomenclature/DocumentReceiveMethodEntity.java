package bg.duosoft.nacidcoredata.domain.entity.nomenclature;


import bg.duosoft.nacidcoredata.domain.entity.nomenclature.base.NomenclatureEntityBase;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "document_receive_method", schema = "nomenclatures")
public class DocumentReceiveMethodEntity implements Serializable, NomenclatureEntityBase<String> {
    @Id
    @Column(name = "code", nullable = false, length = 4)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "document_recipient_flag")
    private Integer documentRecipient;

    @Column(name = "active")
    private Integer active;

    @Column(name = "eservices_require_payment_receipt_flag")
    private Integer eservicesRequirePaymentReceipt;

    @Column(name = "default_flag")
    private Integer defaultFlag;

    @Column(name = "index")
    private Integer index;

    @Column(name = "crf_code")
    private String certificateReceiveFormCode;
}
