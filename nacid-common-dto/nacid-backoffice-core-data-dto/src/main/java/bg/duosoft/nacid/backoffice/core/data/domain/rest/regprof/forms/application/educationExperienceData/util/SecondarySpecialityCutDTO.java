package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.educationExperienceData.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SecondarySpecialityCutDTO {
    private Integer id;
    private String name;
    private String nameEn;
    private Boolean isActive;
    private Integer recordId;
}
