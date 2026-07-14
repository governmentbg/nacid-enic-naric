package bg.duosoft.nacidservicesbe.domain.entity.lib;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationIdIndexIdEntity;
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
 * Date: 16.11.2022
 * Time: 16:51
 */
@Entity
@Table(name = "lib_document_delivery_details", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class DocumentDeliveryDetailsEntity implements Serializable {

    @EmbeddedId
    private ApplicationIdIndexIdEntity id;

    @ManyToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "apn_id", nullable = false)
    @MapsId("applicationId")
    private DocumentDeliveryFullEntity documentDeliveryApplication;

    @Column(name = "bibliographic_data")
    private String bibliographicData;

    @Column(name = "digital_catalogue")
    private Integer digitalCatalogue;

    @Column(name = "bg_library")
    private Integer bgLibrary;

    @Column(name = "foreign_library")
    private Integer foreignLibrary;

    @Column(name = "file_id")
    private String fileId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "relative_path")
    private String relativePath;

    @Column(name = "root_directory")
    private String rootDirectory;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'DOCUMENT_DELIVERY_COPY_TYPE'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "dct_code", referencedColumnName="code"))
    })
    private ReferenceDataEntity documentDeliveryCopyType;
}
