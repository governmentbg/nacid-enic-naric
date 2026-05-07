package bg.duosoft.nacidservicesbe.cloner.entity.biblioreference;

import bg.duosoft.nacidservicesbe.cloner.entity.base.BaseCloner;
import bg.duosoft.nacidservicesbe.cloner.entity.common.ApplicationEntityCloner;
import bg.duosoft.nacidservicesbe.domain.entity.lib.BibliographicReferenceFullEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.04.2023
 * Time: 14:30
 */
@Mapper(componentModel = "spring", uses = {
        ApplicationEntityCloner.class,
        BibliographicReferenceLanguageEntityCloner.class
})
public abstract class BibliographicReferenceEntityCloner extends BaseCloner<BibliographicReferenceFullEntity> {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "id", ignore = true)
    public abstract BibliographicReferenceFullEntity clone(BibliographicReferenceFullEntity source);
}
