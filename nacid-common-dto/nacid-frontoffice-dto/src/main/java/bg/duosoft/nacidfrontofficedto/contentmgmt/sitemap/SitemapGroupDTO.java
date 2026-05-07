package bg.duosoft.nacidfrontofficedto.contentmgmt.sitemap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SitemapGroupDTO {
    private String id;
    private String name;
    private String nameEn;
    private Integer position;
}
