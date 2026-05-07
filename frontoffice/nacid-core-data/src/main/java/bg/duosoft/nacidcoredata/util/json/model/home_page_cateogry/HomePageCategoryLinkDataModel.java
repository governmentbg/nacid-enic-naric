package bg.duosoft.nacidcoredata.util.json.model.home_page_cateogry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePageCategoryLinkDataModel {
    private String id;
    private String name;
    private String nameEn;
    private String href;
    private Integer order;
    private Boolean active;
    private List<String> accessRoles;
}
