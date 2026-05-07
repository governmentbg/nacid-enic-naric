package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GraduationDocTypeConfigEntityPK implements Serializable {

    @Column(name = "graduation_document_type_id", nullable = false)
    private Integer graduationDocTypeId;
    @Column(name = "education_type", nullable = false)
    private String educationType;
}
