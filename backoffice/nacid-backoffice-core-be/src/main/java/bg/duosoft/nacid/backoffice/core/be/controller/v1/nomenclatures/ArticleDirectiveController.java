package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.ArticleDirectiveService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ArticleDirectiveDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ArticleItemDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ArticleDirectiveFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.ARTICLE_DIRECTIVE)
@RequestMapping("/api/v1/article-directives")
public class ArticleDirectiveController extends NomenclatureBaseController<Integer, ArticleDirectiveDTO, ArticleDirectiveFilterDTO> {
    private final ArticleDirectiveService service;

    @GetMapping("/{id}/article-items")
    public List<ArticleItemDTO> selectArticleDirectiveItems(@PathVariable("id") Integer articleDirectiveId) {
        ArticleDirectiveDTO articleDirective = service.selectById(articleDirectiveId);
        return Objects.nonNull(articleDirective) ? articleDirective.getItems() : new ArrayList<>();
    }

    @Override
    protected NomenclatureServiceBase<Integer, ArticleDirectiveDTO, ArticleDirectiveFilterDTO> getService() {
        return service;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }
}
