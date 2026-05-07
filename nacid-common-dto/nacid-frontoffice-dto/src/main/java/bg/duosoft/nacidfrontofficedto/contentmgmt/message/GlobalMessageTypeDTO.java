package bg.duosoft.nacidfrontofficedto.contentmgmt.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class GlobalMessageTypeDTO implements Serializable {
    private String id;
    private String name;
    private String nameEn;
    private Integer typeOrder;
}
