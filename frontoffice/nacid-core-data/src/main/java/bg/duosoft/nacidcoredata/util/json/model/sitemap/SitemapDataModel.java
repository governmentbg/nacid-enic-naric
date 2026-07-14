package bg.duosoft.nacidcoredata.util.json.model.sitemap;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SitemapDataModel {
    private SitemapMainGroupModel mainGroup;
    private List<SitemapSubGroupModel> subGroups;
}
