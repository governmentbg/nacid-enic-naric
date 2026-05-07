package bg.duosoft.nacidfrontofficedto.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateCutDTO implements Serializable {
    private String id;
    private String name;
    private String nameEn;
    private Date createdDate;
    private Date lastUpdateDate;
}
