package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Correspondent {
    private Integer id; // Id на кореспондента в нашата система (информационно, не се попълва)
    private CorrespondentType correspondentType; // Тип на кореспондента
    private Integer correspondentGroupId; // Група на кореспондента (номенклатура)
    private String name; // Наименование, ако е организация
    private String firstName; // Първо име (ако е ФЛ)
    private String middleName; // Второ име (ако е ФЛ)
    private String lastName; // Фамилия (ако е ФЛ)
    private String uin; // ЕГН/БУЛСТАТ
    private Integer externalId; // Вашето Id, винаги ни го пращайте
    private List<CorrespondentContact> correspondentContacts;
}

// Others
//    private Integer version; ? Not sure

//    private Boolean hideUin;
//    private Integer tenantId;// nullable
//    private Integer registerIndexId; //nullable
//    private String search;// this column contains Name + Uin
//    private String foreignerUINType;
//    private Date birthDate;
//    // Company
//    private String legalEntityRegisterName;
//    private String legalEntityOtherData;
//    private Integer docsExchangeProviderId;
//    private String alias;