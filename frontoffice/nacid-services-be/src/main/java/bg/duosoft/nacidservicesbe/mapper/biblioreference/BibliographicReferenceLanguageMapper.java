package bg.duosoft.nacidservicesbe.mapper.biblioreference;

import bg.duosoft.nacidcoredata.mapper.nomenclature.LanguageMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.LanguageDTO;
import bg.duosoft.nacidservicesbe.domain.entity.lib.BibliographicReferenceLanguageEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 28.02.2023
 * Time: 17:58
 */
@Mapper(componentModel = "spring", uses = {
        LanguageMapper.class
})
public abstract class BibliographicReferenceLanguageMapper extends BaseObjectMapper<BibliographicReferenceLanguageEntity, LanguageDTO> {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "language", source = ".")
    public abstract BibliographicReferenceLanguageEntity toEntity(LanguageDTO languageDTO);

    @InheritInverseConfiguration
    public abstract LanguageDTO toDto(BibliographicReferenceLanguageEntity bibliographicReferenceLanguageEntity);
}
