package bg.duosoft.nacidfrontofficedto.contentmgmt.sitemap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SitemapLinkDTO {
    private String subGroupId;
    private String id;
    private String name;
    private String nameEn;
    private String link;
    private Integer position;
}
