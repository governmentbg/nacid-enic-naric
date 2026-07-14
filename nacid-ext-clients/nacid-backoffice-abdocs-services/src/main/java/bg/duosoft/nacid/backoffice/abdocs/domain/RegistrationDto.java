package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationDto {
    private DocRegistrationType docRegistrationType; // Начин на регистриране
    // = DocRegistrationType.ByDocType.value() - Спрямо вида на документа
    // = DocRegistrationType.ByParentDocRegistrationNumber.value() - Спрямо на вида на документа на преписката, към която се връзва този документ (трябва да има parentDocId)
    // = DocRegistrationType.ExternalRegistrationNumber.value() - По външен номер
    private String registrationNumber; // Външен номер (използва се когато DocRegistrationType.ExternalRegistrationNumber.value())
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date regDate;
    private Boolean includeDateInNumber;
}

//    Integer docId // nullable
//    Integer regIndexId // nulable
//    Date regDate
//    Boolean includeDateInNumber
//    Integer parentDocId
//    Integer rootDocId