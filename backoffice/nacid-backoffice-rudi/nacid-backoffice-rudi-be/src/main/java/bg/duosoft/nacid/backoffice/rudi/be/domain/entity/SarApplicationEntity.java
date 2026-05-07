package bg.duosoft.nacid.backoffice.rudi.be.domain.entity;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationStatusHistoryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgSarAppStatusEntity;
import lombok.*;

import javax.persistence.*;

/**
 * User: ggeorgiev
 * Date: 25.01.2023
 * Time: 11:24
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "sar_application", schema = "rudi")
public class SarApplicationEntity {
    @Id
    @Column(name = "apn_id")
    private Integer applicationId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "apn_id")
    private RudiApplicationEntity application;

    @Column(name = "statute_flag")
    private Integer statuteFlag;

    @Column(name = "authenticity_flag")
    private Integer authenticityFlag;

    @Column(name = "recommendation_flag")
    private Integer recommendationFlag;

    @Column(name = "outgoing_number")
    private String outgoingNumber;

    @Column(name = "internal_number")
    private String internalNumber;

    @ManyToOne
    @JoinColumn(name = "statute_final_status_history_id", referencedColumnName = "id")
    private ApplicationStatusHistoryEntity statuteFinalStatus;

    @ManyToOne
    @JoinColumn(name = "authenticity_final_status_history_id", referencedColumnName = "id")
    private ApplicationStatusHistoryEntity authenticityFinalStatus;

    @ManyToOne
    @JoinColumn(name = "recommendation_final_status_history_id", referencedColumnName = "id")
    private ApplicationStatusHistoryEntity recommendationFinalStatus;

}
