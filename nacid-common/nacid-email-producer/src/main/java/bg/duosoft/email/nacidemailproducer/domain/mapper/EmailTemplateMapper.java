package bg.duosoft.email.nacidemailproducer.domain.mapper;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailTemplate;
import bg.duosoft.email.nacidemailproducer.domain.entity.EEmailTemplate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class EmailTemplateMapper extends BaseObjectMapper<EEmailTemplate, CEmailTemplate> {
}
