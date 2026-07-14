package bg.duosoft.nacidfrontofficedto.services.common.application;

import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2023
 * Time: 13:53
 */
@Data
public class DmsDocDetailsDTO {

    private List<DmsDocFileDTO> files;
}
