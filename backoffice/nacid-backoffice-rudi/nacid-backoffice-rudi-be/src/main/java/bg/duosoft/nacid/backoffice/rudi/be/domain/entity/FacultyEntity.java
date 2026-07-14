package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.UniversityEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "university_faculty", schema = "rudi")
public class FacultyEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "active")
    private Integer isActive;

    @ManyToOne
    @JoinColumn(name = "uny_id", referencedColumnName = "id", nullable=false)
    private UniversityEntity university;
}
