package bg.duosoft.nacidservicesbe.cloner.entity.common;

import bg.duosoft.nacidservicesbe.cloner.entity.base.BaseCloner;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationDocumentReceiveMethodEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring")
public abstract class ApplicationDocumentReceiveMethodEntityCloner extends BaseCloner<ApplicationDocumentReceiveMethodEntity> {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", ignore = true)
    public abstract ApplicationDocumentReceiveMethodEntity clone(ApplicationDocumentReceiveMethodEntity source);
}
