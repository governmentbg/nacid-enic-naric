package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.biblio_reference.process_data;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BiblioReferenceProcessDataDTO {
    private Integer applicationId;
    private ReferenceDataDTO searchType;
    private ReferenceDataDTO resultKind;
    private String subject;
    private String keywords;
    private Integer periodRetFrom;
    private Integer periodRetTo;
    private List<String> languages;
}
