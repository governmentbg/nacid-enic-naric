package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 05.01.2023
 * Time: 15:35
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "application_commission_member_specialities", schema = "rudi")
public class ApplicationCommissionMemberSpecialityEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "application_commission_member_id", referencedColumnName = "id", nullable = false)
    private ApplicationCommissionMemberEntity applicationCommissionMember;

    @Column(name = "speciality")
    private String speciality;

}
