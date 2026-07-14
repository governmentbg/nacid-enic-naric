package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DocumentReceiveMethodEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
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
@Table(name = "application_document_receive_method", schema = "common")
public class ApplicationDocumentReceiveMethodEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "id")
    private ApplicationEntity application;

    @ManyToOne
    @JoinColumn(name = "document_receive_method_code", referencedColumnName = "code")
    private DocumentReceiveMethodEntity documentReceiveMethod;

    @ManyToOne
    @JoinColumn(name = "document_recipient_address", referencedColumnName = "id")
    private AddressEntity documentRecipientAddress;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'CERTIFICATE_RECEIVE_FORM'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "crf_code", referencedColumnName = "code"))
    })
    private ReferenceDataEntity crfCode;
}
