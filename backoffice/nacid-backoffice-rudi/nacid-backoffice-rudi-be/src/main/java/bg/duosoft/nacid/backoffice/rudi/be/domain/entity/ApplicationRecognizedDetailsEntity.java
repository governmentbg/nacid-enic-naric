package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ProfGroupEntity;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "application_recognition_details", schema = "rudi")
public class ApplicationRecognizedDetailsEntity implements Serializable {
    @Id
    @Column(name = "apn_id")
    private Integer applicationId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "apn_id")
    private RudiApplicationEntity application;

    @Column(name = "recognized_edu_level")
    private String recognizedEduLevel;

    @Column(name = "recognized_qualification")
    private String recognizedQualification;

    @ManyToOne
    @JoinColumn(name = "recognized_prof_group_id", referencedColumnName = "id")
    private ProfGroupEntity profGroup;
}
