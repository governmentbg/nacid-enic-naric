package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.inquiry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImpactFactorDTO implements Serializable {

    private Integer id;
    private String title;
    private String year;
    private String issn;
    private String impact;
    private String journalRank;

}
