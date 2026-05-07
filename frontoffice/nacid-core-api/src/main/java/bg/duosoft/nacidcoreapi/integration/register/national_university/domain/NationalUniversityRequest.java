package bg.duosoft.nacidcoreapi.integration.register.national_university.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NationalUniversityRequest {
    private Integer institutionActiveStatus;
    private Integer institutionSearchType;
    private Integer limit;
}
