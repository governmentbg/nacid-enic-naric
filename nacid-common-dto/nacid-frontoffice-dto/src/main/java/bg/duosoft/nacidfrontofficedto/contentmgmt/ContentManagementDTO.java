package bg.duosoft.nacidfrontofficedto.contentmgmt;

import lombok.Data;

@Data
public class ContentManagementDTO {
    private String id;
    private String dataTemplate;
    private String type;
    private String data;
    private Integer contentOrder;
    private String alias;
    private Boolean active;
}
