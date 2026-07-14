package bg.duosoft.nacidfrontofficedto.services.duplicate;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DuplicateDetailDTO {
    private Integer id;
    private String originalDocumentNumber;
    private String additionalInfo;
}
