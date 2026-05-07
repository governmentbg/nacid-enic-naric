package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Cacheable(value = false)
@Table(name = "document_receive_method", schema = "nomenclatures")
public class DocumentReceiveMethodEntity implements Serializable, StringKeyNomenclatureEntityBase {
    @Id
    @Column(name = "code", nullable = false, length = 4)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private Integer active;

    @Column(name = "document_recipient_flag", nullable = false)
    private Integer documentRecipientFlag;

    @Column(name = "eservices_require_payment_receipt_flag", nullable = false)
    private Integer eservicesRequirePaymentReceiptFlag;

    @Column(name = "index", nullable = false)
    private Integer index;

    @Column(name = "default_flag", nullable = false)
    private Integer defaultFlag;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'CERTIFICATE_RECEIVE_FORM'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "crf_code", referencedColumnName = "code"))
    })
    private ReferenceDataEntity crfCode;

}