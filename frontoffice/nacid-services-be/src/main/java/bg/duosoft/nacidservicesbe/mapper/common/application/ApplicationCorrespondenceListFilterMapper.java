package bg.duosoft.nacidservicesbe.mapper.common.application;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCorrespondenceListFilterDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationCorrespondenceFilter;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.10.2023
 * Time: 15:50
 */
@Mapper(componentModel = "spring")
public abstract class ApplicationCorrespondenceListFilterMapper {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    public abstract ApplicationCorrespondenceFilter toEntity(ApplicationCorrespondenceListFilterDTO filterDTO);
}
