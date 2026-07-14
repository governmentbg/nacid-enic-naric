package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.biblio_reference.reception;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.reception.LibservBaseReceptionDTO;
import lombok.Data;

import java.util.List;

@Data
public class BiblioReferenceReceptionDTO extends LibservBaseReceptionDTO {
    private Boolean nacidSearchType;
    private Boolean foreignSearchType;
    private String nacidResultKind;
    private String foreignResultKind;
    private String subject;
    private String keywords;
    private Integer periodRetFrom;
    private Integer periodRetTo;
}
