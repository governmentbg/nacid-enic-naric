package bg.duosoft.nacidcoredata.util.json.model.admin_console;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminConsoleSectionModel {
    private String id;
    private String title;
    private String titleEn;
    private List<String> accessRoles;
    private List<AdminConsolePageModel> pages;
    private Integer position;
    private Integer idIndex;

    public void updateData(String title, String titleEn, Integer position, List<String> accessRoles) {
        this.title = title;
        this.titleEn = titleEn;
        this.position = position;
        this.accessRoles = accessRoles;
    }
}
