package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Attachments {
    @ToString.Exclude private byte[] content; // Съдържанието на файла в byte array
    private String mimeType; // Тип на данните
    private String name; // Пълно име на файла
}