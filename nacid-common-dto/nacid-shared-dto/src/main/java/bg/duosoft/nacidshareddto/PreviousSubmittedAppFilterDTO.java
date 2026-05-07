package bg.duosoft.nacidshareddto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PreviousSubmittedAppFilterDTO {
    private Integer applicantId;
    private String personalIdType;
    private String personalId;
    private String foreignerIdentifierKind;
    private String foreignerIdentifierCountry;
    private String personalNacidId;
    private boolean extractAll;
    private String firstName;
    private String secondName;
    private String lastName;
    private LocalDate birthDate;
    private String originCountryCode;
}
