package bg.duosoft.nacidfrontofficedto.contentmgmt.ac;

import lombok.Data;

import java.util.List;

@Data
public class AdminConsolePageDTO {
    private String id;
    private String parentId;
    private String title;
    private String titleEn;
    private List<String> accessRoles;
    private Integer position;
    private String href;
}
