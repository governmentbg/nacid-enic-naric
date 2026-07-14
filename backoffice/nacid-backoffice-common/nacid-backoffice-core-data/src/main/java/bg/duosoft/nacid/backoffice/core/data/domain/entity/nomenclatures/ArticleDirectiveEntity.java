package bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Cacheable(value = false)
@Table(name = "regprof_article_directive", schema = "nomenclatures")
public class ArticleDirectiveEntity implements Serializable, IntegerKeyNomenclatureEntityBase {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private Integer active;


    @OneToMany(mappedBy = "articleDirective", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleItemEntity> items;
}
