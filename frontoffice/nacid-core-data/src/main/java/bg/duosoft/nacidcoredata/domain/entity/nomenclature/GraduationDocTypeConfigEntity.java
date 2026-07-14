package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 18:05
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "cfg_graduation_document_type_config", schema = "nomenclatures")
public class GraduationDocTypeConfigEntity implements Serializable {

    @EmbeddedId
    private GraduationDocTypeConfigEntityPK id;

    @ManyToOne
    @MapsId(value = "graduationDocTypeId")
    @JoinColumn(name = "graduation_document_type_id", referencedColumnName = "id", insertable = false,updatable = false)
    @JsonIgnore// v cron app-a pravq json ot entity-tata za da logvam kakvo e promenqno, zatova slagam @JsonIgnore na tova property, inache se poluchava zaciklqne!!!
    @ToString.Exclude
    private GraduationDocTypeEntity graduationDocumentType;
}
