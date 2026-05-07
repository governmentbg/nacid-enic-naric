package bg.duosoft.nacidfrontofficedto.services.common.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalizeApplicationResultDTO {
    private String docId;
    private String signedFileUuid;
}
