package bg.duosoft.nacidcoredata.mapper;

import bg.duosoft.nacidcoredata.domain.entity.ContentManagementEntity;
import bg.duosoft.nacidfrontofficedto.contentmgmt.ContentManagementDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public abstract class ContentManagementMapper extends BaseObjectMapper<ContentManagementEntity, ContentManagementDTO> {
}
