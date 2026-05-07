package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocCorrespondent {
	private Integer id; // Id на DocCorrespondents в нашата система (информационно, не се попълва)
	private Integer docId; // DocId в нашата система (информационно, не се попълва)
	private Integer correspondentId; // CorrespondentId в нашата система (информационно, не се попълва)
	private Correspondent correspondent;
	private DocCorrespondentType docCorrespondentType;
	private Integer docDestinationTypeId; // Изпратено чрез (DestinationType enum)
	private String correspondentContactId; // Избор на адрес за кореспондиране (от кореспондента) за конкретния документ.
	// Вашето Id, като трябва адреса да го има въведен и в нашата система
}