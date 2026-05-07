package bg.duosoft.nacidfrontofficedto.contentmgmt.sitemap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SitemapSubGroupDTO {
    private String mainGroupId;
    private String id;
    private String name;
    private String nameEn;
    private Integer position;
}
