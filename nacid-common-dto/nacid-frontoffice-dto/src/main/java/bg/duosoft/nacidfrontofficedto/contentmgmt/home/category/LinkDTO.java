package bg.duosoft.nacidfrontofficedto.contentmgmt.home.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDTO {
    private String categoryId;
    private String id;
    private String name;
    private String nameEn;
    private String href;
    private Integer order;
    private Boolean active;
    private List<String> accessRoles;
}
