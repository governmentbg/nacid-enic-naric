package bg.duosoft.nacidfrontofficedto.email;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class NotificationDTO implements Serializable {
    private Integer id;
    private String subject;
    private String recipients;
    private Date createdDate;
    private Date sentDate;
    private Boolean skipSending;
}
