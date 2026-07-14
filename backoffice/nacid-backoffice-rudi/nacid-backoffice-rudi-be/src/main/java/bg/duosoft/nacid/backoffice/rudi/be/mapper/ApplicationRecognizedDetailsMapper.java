package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationRecognizedDetailsDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationRecognizedSpecialityDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationRecognizedDetailsEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationRecognizedSpecialityEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class ApplicationRecognizedDetailsMapper extends BaseObjectMapper<ApplicationRecognizedDetailsEntity, ApplicationRecognizedDetailsDTO> {
    @AfterMapping
    protected void afterToDTO(ApplicationRecognizedSpecialityEntity source, @MappingTarget ApplicationRecognizedSpecialityDTO target) {
        target.setApplicationId(source.getApplication().getId());
    }
}
