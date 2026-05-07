package bg.duosoft.nacidfrontofficedto.contentmgmt.home.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private String id;
    private String icon;
    private String name;
    private String nameEn;
    private Integer order;
    private Boolean active;
    private Boolean showInServicesPage;
    private List<String> accessRoles;
}
