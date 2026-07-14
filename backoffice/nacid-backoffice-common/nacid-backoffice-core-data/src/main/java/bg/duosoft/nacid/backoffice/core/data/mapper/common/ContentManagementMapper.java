package bg.duosoft.nacid.backoffice.core.data.mapper.common;


import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ContentManagementEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ContentManagementDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public abstract class ContentManagementMapper extends BaseObjectMapper<ContentManagementEntity, ContentManagementDTO> {
}
