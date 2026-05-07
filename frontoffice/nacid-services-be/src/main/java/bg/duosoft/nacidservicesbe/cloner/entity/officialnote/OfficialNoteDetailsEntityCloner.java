package bg.duosoft.nacidservicesbe.cloner.entity.officialnote;

import bg.duosoft.nacidservicesbe.cloner.entity.base.BaseCloner;
import bg.duosoft.nacidservicesbe.domain.entity.lib.OfficialNoteDetailsEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 10.04.2023
 * Time: 14:01
 */
@Mapper(componentModel = "spring")
public abstract class OfficialNoteDetailsEntityCloner extends BaseCloner<OfficialNoteDetailsEntity> {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "officialNoteApplication", ignore = true)
    public abstract OfficialNoteDetailsEntity clone(OfficialNoteDetailsEntity source);
}
