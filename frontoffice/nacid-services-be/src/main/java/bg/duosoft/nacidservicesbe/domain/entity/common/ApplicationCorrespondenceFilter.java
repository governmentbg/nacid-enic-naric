package bg.duosoft.nacidservicesbe.domain.entity.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.10.2023
 * Time: 14:51
 */
@Getter
@Setter
public class ApplicationCorrespondenceFilter {

    private String user;
    private LocalDate dateCreatedFrom;
    private LocalDate dateCreatedTo;
    private LocalDate dateReadFrom;
    private LocalDate dateReadTo;
    private LocalDate registrationDateFrom;
    private LocalDate registrationDateTo;
    private String tempNumber;
    private String registrationNumber;
    private Boolean read;

    private Integer page;
    private Integer pageSize;
    private String order;
    private String orderBy;
}
