package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.*;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Cacheable(value = false)
@Table(name = "legal_reason", schema = "nomenclatures")
public class LegalReasonEntity implements IntegerKeyNomenclatureEntityBase {
    @Id
    @Column(name = "id", nullable = false)
//    @GeneratedValue(generator = "lrn-sequence-generator")
//    @GenericGenerator(strategy = "bg.duosoft.nacid.backoffice.core.data.domain.CustomSequenceGenerator", name = "lrn-sequence-generator", parameters = {@org.hibernate.annotations.Parameter(name = "sequence", value = "nomenclatures.legal_reason_id_seq")})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private Integer active;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'APPLICATION_STATUS'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "status_code", referencedColumnName="code"))
    })
    private ReferenceDataEntity applicationStatus;

    @Column(name = "ordinance_article")
    private String ordinanceArticle;

    @Column(name = "regulation_article")
    private String regulationArticle;

    @Column(name = "regulation_text")
    private String regulationText;

    @OneToMany(mappedBy = "legalReason", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CfgLegalReasonToAppTypeEntity> configs;

    public LegalReasonEntity(Integer id) {
        this.id = id;
    }

}