package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonUniversityAdditionalDetailsDTO {
    private Integer universityId;
    private String letterRecipient;
    private String letterGreeting;

    public PersonUniversityAdditionalDetailsDTO(Integer universityId) {
        this.universityId = universityId;
    }
}
