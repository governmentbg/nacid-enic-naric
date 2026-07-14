package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.ArticleDirectiveRepository;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.ArticleDirectiveValidator;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ArticleDirectiveDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ArticleDirectiveFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ArticleDirectiveMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleDirectiveService extends NomenclatureServiceBase<Integer, ArticleDirectiveDTO, ArticleDirectiveFilterDTO> {
    private final ArticleDirectiveRepository repository;
    private final ArticleDirectiveMapper mapper;
    private final ArticleDirectiveValidator validator;

    @Override
    protected ArticleDirectiveRepository getNomenclaturesRepository() {
        return repository;
    }

    @Override
    protected ArticleDirectiveMapper getNomenclaturesMapper() {
        return mapper;
    }

    @Override
    protected BaseNomenclatureValidator<Integer, ArticleDirectiveDTO, ArticleDirectiveFilterDTO> getValidator() {
        return validator;
    }

}
