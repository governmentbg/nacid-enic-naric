package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ArticleDirectiveDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ArticleItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionExperienceExaminationDTO {
    private Integer id;
    private Boolean isExperienceDocumentRecognized;
    private ArticleItemDTO articleItem;
    private ArticleDirectiveDTO articleDirective;
    private List<AttachedDocDTO> attachedDocs;
}
