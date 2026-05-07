package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.inquiry;

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
public class PublicationDTO implements Serializable {

    private Integer id;
    private String publication;
    private String sortPublicationField;
    private List<CitationDTO> citations;
}
