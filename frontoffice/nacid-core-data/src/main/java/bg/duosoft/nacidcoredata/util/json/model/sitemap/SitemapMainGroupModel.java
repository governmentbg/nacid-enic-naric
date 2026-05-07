package bg.duosoft.nacidcoredata.util.json.model.sitemap;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SitemapMainGroupModel {
    private String id;
    private String name;
    private String nameEn;
    private Integer position;
    private Integer idIndex;


    public void updateData(String name, String nameEn, Integer position) {
        this.name = name;
        this.nameEn = nameEn;
        this.position = position;
    }
}
