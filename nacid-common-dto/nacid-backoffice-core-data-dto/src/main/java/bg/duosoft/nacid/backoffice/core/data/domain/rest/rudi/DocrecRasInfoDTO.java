package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocrecRasInfoDTO {
    private Integer applicationId;
    private Boolean doesMeetTransferRequirements;
    private String externalSystemId;
    private String externalLink;
}
