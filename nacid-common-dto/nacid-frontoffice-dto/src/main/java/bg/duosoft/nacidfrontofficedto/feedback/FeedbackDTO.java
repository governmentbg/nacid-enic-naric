package bg.duosoft.nacidfrontofficedto.feedback;

import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FeedbackDTO implements Serializable {
    private Integer id;
    private String name;
    private String text;
    private FileStoreEntryDTO file;
    private Boolean isRead;
    private Date createdDate;
    private Date lastUpdateDate;
    private String userLastUpdate;
    private String captchaToken;
}
