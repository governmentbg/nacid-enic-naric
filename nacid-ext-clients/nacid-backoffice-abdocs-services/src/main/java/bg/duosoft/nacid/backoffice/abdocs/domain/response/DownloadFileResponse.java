package bg.duosoft.nacid.backoffice.abdocs.domain.response;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DownloadFileResponse {

    private byte[] content;
    private String type;
    private String fileName;

}
