package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocUnit {
    private Integer unitId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date createDate; //Дата и час в момента
    private Integer docUnitRole; // Тип най-често ви трябва 2 - "ДО"
}

//    Integer docId
//    Integer docActionRequestId
//    Integer parentDocUnitId
//    ListDocUnit> childrenUnits
//    Integer craeteUnitId