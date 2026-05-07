package bg.duosoft.nacidfrontofficedto.contentmgmt.law;
import lombok.Data;

import java.util.List;


@Data
public class LawDTO {
    private String description;
    private List<LawFileDTO> files;
}
