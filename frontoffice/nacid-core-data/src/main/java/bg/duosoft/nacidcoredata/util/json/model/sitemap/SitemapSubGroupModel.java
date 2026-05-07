package bg.duosoft.nacidcoredata.util.json.model.sitemap;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SitemapSubGroupModel {
    private String id;
    private String name;
    private String nameEn;
    private Integer position;
    private Integer idIndex;
    private List<SitemapLinkModel> links;

    public void updateData(String name, String nameEn, Integer position) {
        this.name = name;
        this.nameEn = nameEn;
        this.position = position;
    }
}
