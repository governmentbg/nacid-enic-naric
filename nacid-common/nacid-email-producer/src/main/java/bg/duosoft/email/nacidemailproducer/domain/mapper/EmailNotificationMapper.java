package bg.duosoft.email.nacidemailproducer.domain.mapper;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailNotification;
import bg.duosoft.email.nacidemailproducer.domain.entity.EEmailNotification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class EmailNotificationMapper extends BaseObjectMapper<EEmailNotification, CEmailNotification> {
}
