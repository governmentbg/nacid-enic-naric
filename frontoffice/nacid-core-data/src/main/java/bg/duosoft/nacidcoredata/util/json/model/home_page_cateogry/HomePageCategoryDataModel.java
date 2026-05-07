package bg.duosoft.nacidcoredata.util.json.model.home_page_cateogry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePageCategoryDataModel {
    private String id;
    private String icon;
    private String name;
    private String nameEn;
    private Integer order;
    private Boolean active;
    private Boolean showInServicesPage;
    private List<String> accessRoles;
    List<HomePageCategoryLinkDataModel> links;
}
