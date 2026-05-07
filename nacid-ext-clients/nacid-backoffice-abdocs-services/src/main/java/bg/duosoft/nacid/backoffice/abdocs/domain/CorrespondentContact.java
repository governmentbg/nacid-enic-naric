package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CorrespondentContact {
	private Integer id; // Id на контакта в нашата база (информационно, не се попълва)
	private Integer correspondentId; // Id към кой кореспондент е контакта в нашата база (информационно, не се попълва)
	private String name; // Име на кореспондента
	private String email; // Имейл
	private String phone; // Телефон
	private String address; // Адрес
	private String fax; // Факс
	private String postCode; // Пощенски код
	private Country country; // Dto обект, в който се намира код-а, по който ще се сет-ва
	private Integer settlementId; // Id на населеното място в нашата система
	private String foreignerSettlement; // Име на населено място, ако е чужденец
	private Integer externalId; // Вашето Id
}

// Other fields from doc.jav
//	String postOfficeBox
//	Integer districtId
//	Integer municipalityId
//	String displayName