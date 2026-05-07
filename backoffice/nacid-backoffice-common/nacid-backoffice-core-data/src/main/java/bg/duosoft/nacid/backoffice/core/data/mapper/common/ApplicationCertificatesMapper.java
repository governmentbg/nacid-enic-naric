package bg.duosoft.nacid.backoffice.core.data.mapper.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationCertificatesEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationCertificatesDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public abstract class ApplicationCertificatesMapper extends BaseObjectMapper<ApplicationCertificatesEntity, ApplicationCertificatesDTO> {
}
