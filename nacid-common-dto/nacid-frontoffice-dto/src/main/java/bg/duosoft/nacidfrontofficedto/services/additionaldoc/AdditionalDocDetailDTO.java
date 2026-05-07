package bg.duosoft.nacidfrontofficedto.services.additionaldoc;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.06.2022
 * Time: 11:16
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdditionalDocDetailDTO {
    private Integer id;
    private String entryNumber;
    private String additionalInfo;
}
