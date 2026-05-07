package bg.duosoft.nacidfrontofficedto.services.common.education;

import bg.duosoft.nacidfrontofficedto.utils.constants.DTOConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.06.2022
 * Time: 11:21
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiplomaDTO {
    private String series;
    private String number;
    private String registrationNumber;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = DTOConstants.DATE_FORMAT)
    private LocalDate date;
}
