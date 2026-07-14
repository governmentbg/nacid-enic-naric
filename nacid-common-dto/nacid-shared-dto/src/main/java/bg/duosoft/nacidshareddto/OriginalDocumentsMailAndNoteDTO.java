package bg.duosoft.nacidshareddto;

import lombok.Data;

@Data
public class OriginalDocumentsMailAndNoteDTO {
    private Integer application;
    private String emailAddress;
    private String emailText;
    private String noteText;
}
