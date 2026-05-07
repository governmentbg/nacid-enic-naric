package bg.duosoft.nacidfrontofficedto.contentmgmt.message;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GlobalMessageDTO implements Serializable {
    private Integer id;
    private String name;
    private String nameEn;
    private String text;
    private String textEn;
    private GlobalMessageTypeDTO type;
    private Date createdDate;
    private Date lastUpdateDate;
    private Boolean active;
    private String createdUserId;
    private String lastUpdateUserId;
}
