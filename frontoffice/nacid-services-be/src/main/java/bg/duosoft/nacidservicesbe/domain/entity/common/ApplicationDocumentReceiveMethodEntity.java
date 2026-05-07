package bg.duosoft.nacidservicesbe.domain.entity.common;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.DocumentReceiveMethodEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.05.2024
 * Time: 13:10
 */
@Entity
@Table(name = "application_document_receive_method", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class ApplicationDocumentReceiveMethodEntity implements Serializable {

    @EmbeddedId
    private ApplicationIdIndexIdEntity id;

    @OneToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "id")
    @MapsId("applicationId")
    private ApplicationEntity application;

    @ManyToOne
    @JoinColumn(name = "document_receive_method_code")
    private DocumentReceiveMethodEntity documentReceiveMethod;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "document_recipient_address")
    private AddressEntity documentRecipientAddress;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'CERTIFICATE_RECEIVE_FORM'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "crf_code", referencedColumnName="code"))
    })
    private ReferenceDataEntity certificateReceiveForm;
}
