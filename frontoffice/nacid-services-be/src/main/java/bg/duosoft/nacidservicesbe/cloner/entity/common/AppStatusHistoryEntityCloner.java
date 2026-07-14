package bg.duosoft.nacidservicesbe.cloner.entity.common;

import bg.duosoft.nacidservicesbe.cloner.entity.base.BaseCloner;
import bg.duosoft.nacidservicesbe.domain.entity.common.AppStatusHistoryEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.04.2023
 * Time: 15:06
 */
@Mapper(componentModel = "spring")
public abstract class AppStatusHistoryEntityCloner extends BaseCloner<AppStatusHistoryEntity> {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", ignore = true)
    @Mapping(target = "statusName", ignore = true)
    public abstract AppStatusHistoryEntity clone(AppStatusHistoryEntity source);
}
