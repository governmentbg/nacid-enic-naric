package bg.duosoft.nacidfrontofficedto.services.common.application;

import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2023
 * Time: 15:39
 */
@Data
public class ApplicationCertificateDTO {

    private String certificateNumber;
    private Integer applicationAttachedDocId;
    private List<DmsDocFileDTO> files;
}
