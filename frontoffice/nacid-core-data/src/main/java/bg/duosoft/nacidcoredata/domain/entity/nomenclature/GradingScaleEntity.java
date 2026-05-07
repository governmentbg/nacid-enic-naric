package bg.duosoft.nacidcoredata.domain.entity.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.base.NomenclatureEntityBase;
import bg.duosoft.nacidfrontofficedto.services.common.application.ScaleTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "grading_scale", schema = "services")
public class GradingScaleEntity implements NomenclatureEntityBase<Integer> {
    @Id
    private Integer id;

    @Column(name = "scale_name")
    private String name;

    @Column(name = "active")
    private Integer active;

    @Column(name = "scale_type")
    @Enumerated(EnumType.STRING)
    private ScaleTypeEnum scaleType;

    @Column(name = "start_year")
    private Integer startYear;

    @Column(name = "end_year")
    private Integer endYear;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "country_code", referencedColumnName = "code")
    private CountryEntity country;

    @Column(name = "alternate_key")
    private String alternateKey;

}
