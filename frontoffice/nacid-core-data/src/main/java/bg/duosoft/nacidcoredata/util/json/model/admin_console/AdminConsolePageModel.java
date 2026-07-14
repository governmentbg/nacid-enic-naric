package bg.duosoft.nacidcoredata.util.json.model.admin_console;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminConsolePageModel {
    private String id;
    private String parentId;
    private String title;
    private String titleEn;
    private String href;
    private List<AdminConsolePageModel> children;
    private List<String> accessRoles;
    private Integer position;
    private Integer idIndex;

    public void updateData(String title, String titleEn, String href, Integer position, List<String> accessRoles) {
        this.title = title;
        this.titleEn = titleEn;
        this.position = position;
        this.href = href;
        this.accessRoles = accessRoles;
    }
}
