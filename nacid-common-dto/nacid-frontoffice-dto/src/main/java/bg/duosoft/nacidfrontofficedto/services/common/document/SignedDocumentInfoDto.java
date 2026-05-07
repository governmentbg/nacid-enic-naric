package bg.duosoft.nacidfrontofficedto.services.common.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class SignedDocumentInfoDto {
    private Integer id;
    private String fileId;
    private Boolean customerSigned;
    private List<String> entryNumbers;
}
