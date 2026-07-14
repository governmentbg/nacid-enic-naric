package bg.duosoft.nacid.ras.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FileStorageResponse {
    private String key;
    private String name;
    private String hash;
    private Long size;
    private Integer dbId;
    private String mimeType;
}
