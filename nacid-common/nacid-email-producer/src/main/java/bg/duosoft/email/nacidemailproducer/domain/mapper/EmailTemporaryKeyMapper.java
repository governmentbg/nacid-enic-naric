package bg.duosoft.email.nacidemailproducer.domain.mapper;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailTemporaryKey;
import bg.duosoft.email.nacidemailproducer.domain.entity.EEmailTemporaryKey;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class EmailTemporaryKeyMapper extends BaseObjectMapper<EEmailTemporaryKey, CEmailTemporaryKey> {
}
