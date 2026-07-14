package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.SarApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationStatusHistoryMapper;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.SarApplicationEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, ApplicationStatusHistoryMapper.class})
public abstract class SarApplicationMapper extends BaseObjectMapper<SarApplicationEntity, SarApplicationDTO> {
    @Mapping(target = "isStatute", source = "statuteFlag")
    @Mapping(target = "isAuthenticity", source = "authenticityFlag")
    @Mapping(target = "isRecommendation", source = "recommendationFlag")
    @Mapping(target = "statuteFinalStatus", source = "statuteFinalStatus")
    @Mapping(target = "authenticityFinalStatus", source = "authenticityFinalStatus")
    @Mapping(target = "recommendationFinalStatus", source = "recommendationFinalStatus")
    public abstract SarApplicationDTO toDto(SarApplicationEntity sarApplicationEntity);
}
