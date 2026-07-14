package bg.duosoft.nacidcoreapi.integration.register.national_university.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NationalUniversityModel {
    private String uic;
    private String name;
    private String nameAlt;
    private SettlementModel settlement;
    private String address;
    private String addressAlt;
    private String postCode;
    private String webPageUrl;
}
