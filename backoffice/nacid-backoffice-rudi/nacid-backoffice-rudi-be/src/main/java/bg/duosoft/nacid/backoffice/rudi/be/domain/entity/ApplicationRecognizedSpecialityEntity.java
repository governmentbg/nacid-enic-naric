package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "application_recognized_speciality", schema = "rudi")
public class ApplicationRecognizedSpecialityEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "apn_id", nullable = false)
    private RudiApplicationEntity application;

    @Column(name = "speciality")
    private String speciality;
}
