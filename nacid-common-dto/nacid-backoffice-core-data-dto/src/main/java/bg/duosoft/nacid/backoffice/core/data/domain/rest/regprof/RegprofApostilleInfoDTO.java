package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegprofApostilleInfoDTO {
    private Integer applicationId;
    private Boolean transferAvailable;
    private Boolean doesMeetTransferRequirements;
    private String externalSystemId;
    private LocalDateTime externalSystemDate;
    private String externalLink;
}
