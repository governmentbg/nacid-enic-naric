package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileDto {
	private Integer dbId; // Номер на базата, в която се намира файла
	private String name; // име на файла
	private UUID key; // Взима се след, като се запише файла в базата FileStorage
	private boolean manuallyInserted; // трябва да е true;
}

//	String content
