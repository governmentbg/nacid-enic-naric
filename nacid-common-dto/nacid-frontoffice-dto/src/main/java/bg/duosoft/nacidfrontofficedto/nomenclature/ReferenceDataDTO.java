package bg.duosoft.nacidfrontofficedto.nomenclature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceDataDTO {
    private String domain;
    private String domainName;
    private String id;
    private String name;
    private Integer index;
    private Boolean isActive;

    public ReferenceDataDTO(String domain, String id) {
        this.domain = domain;
        this.id = id;
    }
}
