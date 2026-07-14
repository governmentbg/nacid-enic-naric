package bg.duosoft.email.nacidemailproducer.domain.core;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CEmailTemplate implements Serializable {
    private String id;
    private String name;
    private String nameEn;
    private String subject;
    private String text;
    private Date createdDate;
    private Date lastUpdateDate;
    private Boolean isHtml;
    private String userCreate;
    private String userLastUpdate;
    private String params;
}
