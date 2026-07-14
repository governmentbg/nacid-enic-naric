package bg.duosoft.nacidservicesbe.mapper.common.application;

import bg.duosoft.nacidcoredata.mapper.FoApplicationStatusMapper;
import bg.duosoft.nacidfrontofficedto.services.common.application.AppStatusHistoryDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.AppStatusHistoryEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 25.01.2023
 * Time: 17:09
 */
@Mapper(componentModel = "spring", uses = {FoApplicationStatusMapper.class })
public abstract class AppStatusHistoryMapper extends BaseObjectMapper<AppStatusHistoryEntity, AppStatusHistoryDTO> {


    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "foStatus.pk.id", source = "foStatus")
    public abstract AppStatusHistoryEntity toEntity(AppStatusHistoryDTO appStatusHistoryDTO);

    @InheritInverseConfiguration
    @Mapping(target = "statusName", source = "statusName")
    public abstract AppStatusHistoryDTO toDto(AppStatusHistoryEntity appStatusHistoryEntity);
}
