package bg.duosoft.nacidservicesbe.cloner.entity.biblioreference;

import bg.duosoft.nacidservicesbe.cloner.entity.base.BaseCloner;
import bg.duosoft.nacidservicesbe.domain.entity.lib.BibliographicReferenceLanguageEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.04.2023
 * Time: 15:08
 */
@Mapper(componentModel = "spring")
public abstract class BibliographicReferenceLanguageEntityCloner extends BaseCloner<BibliographicReferenceLanguageEntity> {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bibliographicReferenceApplication", ignore = true)
    public abstract BibliographicReferenceLanguageEntity clone(BibliographicReferenceLanguageEntity source);
}
