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
public class PublicationPeriodDTO implements Serializable {

    private Integer id;
    private Integer year;
    private List<PublicationDTO> publications;
}
