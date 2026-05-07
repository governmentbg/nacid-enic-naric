package bg.duosoft.nacid.backoffice.core.data.domain.entity.common;


import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ApplicationSubtypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ApplicationTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "vw_applications_by_person", schema = "common")
@Cacheable(value = false)
public class VApplicationsByPersonEntity implements Serializable {

    @EmbeddedId
    private VApplicationsByPersonEntityPK pk;

    @Column(name = "entry_num")
    private String entryNum;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'APPLICATION_STATUS'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "status_code", referencedColumnName = "code"))
    })
    private ReferenceDataEntity status;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula = @JoinFormula(value = "'DOCFLOW_STATUS'", referencedColumnName = "domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "docflow_status_code", referencedColumnName = "code"))
    })
    private ReferenceDataEntity docflowStatus;

    @ManyToOne
    @JoinColumn(name = "ate_code", referencedColumnName = "code")
    private ApplicationTypeEntity applicationType;

    @ManyToOne
    @JoinColumn(name = "ase_code", referencedColumnName = "code")
    private ApplicationSubtypeEntity applicationSubtype;

}
