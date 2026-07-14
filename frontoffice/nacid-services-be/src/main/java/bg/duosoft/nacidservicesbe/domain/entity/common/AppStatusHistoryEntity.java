package bg.duosoft.nacidservicesbe.domain.entity.common;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.10.2022
 * Time: 15:37
 */
@Entity
@Table(name = "app_status_history", schema = "services")
@Getter
@Setter
@Cacheable(false)
public class AppStatusHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "apn_id", referencedColumnName = "id", nullable = false)
    private ApplicationEntity application;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'FO_APP_STATUS'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "status_code", referencedColumnName="code"))
    })
    private ReferenceDataEntity foStatus;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(formula=@JoinFormula(value="'APPLICATION_STATUS'", referencedColumnName="domain")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "bo_status_code", referencedColumnName="code"))
    })
    private ReferenceDataEntity boStatus;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "reason_msg")
    private String reasonMessage;

    @Column(name = "user_created")
    private String userCreated;

    public String getStatusName(){
        if(foStatus != null){
            return foStatus.getName();
        } else if(boStatus != null){
            return boStatus.getName();
        }
        return null;
    }

}
