package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.biblio_ref;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.LibservAppDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.LibservObject;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BibliographicReferenceAppDTO implements Serializable, LibservObject {

    private LibservAppDTO libservApp;
    private ReferenceDataDTO searchType;
    private ReferenceDataDTO resultKind;
    private String subject;
    private String keywords;
    private Integer periodRetFrom;
    private Integer periodRetTo;
    private List<BibliographicReferenceLangDTO> languages;

    public BibliographicReferenceAppDTO(LibservAppDTO libservApp) {
        this.libservApp = libservApp;
    }
}
