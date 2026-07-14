package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Cacheable(value = false)
@Table(name = "regprof_article_item", schema = "nomenclatures")
public class ArticleItemEntity implements IntegerKeyNomenclatureEntityBase {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private Integer active;

    @Column(name = "qualification_level_label")
    private String qualificationLevelLabel;


    @ManyToOne
    @JoinColumn(name = "article_directive_id", referencedColumnName = "id")
    private ArticleDirectiveEntity articleDirective;
}
