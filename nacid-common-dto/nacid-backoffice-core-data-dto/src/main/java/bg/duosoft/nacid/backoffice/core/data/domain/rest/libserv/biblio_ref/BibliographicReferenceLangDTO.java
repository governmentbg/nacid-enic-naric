package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.biblio_ref;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BibliographicReferenceLangDTO implements Serializable {

    private String languageCode;

}
