package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FacultyDTO {
    private Integer id;
    private String name;
    private String originalName;
    private Boolean isActive;
    private Integer usageCount;
}
