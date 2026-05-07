package bg.duosoft.nacidfrontofficedto.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateFullDTO extends TemplateCutDTO {

    private String subject;
    private String text;
    private Boolean isHtml;
    private String userCreate;
    private String userLastUpdate;
    private String params;
}
