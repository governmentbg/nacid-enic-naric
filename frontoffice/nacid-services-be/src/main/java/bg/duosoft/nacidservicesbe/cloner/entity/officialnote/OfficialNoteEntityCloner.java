package bg.duosoft.nacidservicesbe.cloner.entity.officialnote;

import bg.duosoft.nacidservicesbe.cloner.entity.base.BaseCloner;
import bg.duosoft.nacidservicesbe.cloner.entity.common.ApplicationEntityCloner;
import bg.duosoft.nacidservicesbe.domain.entity.lib.OfficialNoteFullEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 10.04.2023
 * Time: 13:59
 */
@Mapper(componentModel = "spring", uses = {
        ApplicationEntityCloner.class,
        OfficialNoteDetailsEntityCloner.class,
})
public abstract class OfficialNoteEntityCloner  extends BaseCloner<OfficialNoteFullEntity> {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "officialNoteDetails", ignore = true)
    public abstract OfficialNoteFullEntity clone(OfficialNoteFullEntity source);
}
