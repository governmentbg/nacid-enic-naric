package bg.duosoft.nacidcoredata.util.json.model.sitemap;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SitemapLinkModel {
    private String id;
    private String name;
    private String nameEn;
    private String link;
    private Integer position;
    private Integer idIndex;

    public void updateData(String name, String nameEn, String link, Integer position) {
        this.name = name;
        this.nameEn = nameEn;
        this.link = link;
        this.position = position;
    }
}
