package bg.duosoft.nacidfrontofficedto.services.common.application;

import lombok.Data;

import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.10.2023
 * Time: 15:09
 */
@Data
public class ApplicationCorrespondenceListFilterDTO {

    private String user;
    private LocalDate dateCreatedFrom;
    private LocalDate dateCreatedTo;
    private LocalDate dateReadFrom;
    private LocalDate dateReadTo;
    private LocalDate registrationDateFrom;
    private LocalDate registrationDateTo;
    private String registrationNumber;
    private Boolean read;

    private Integer page;
    private Integer pageSize;
    private String order;
    private String orderBy;
}
