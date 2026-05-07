package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.abdocs.util.AbdocsUrlBuilder;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationCommissionMemberStatementDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationAttachedDocMapper;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationCommissionMemberStatementEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import java.util.Objects;

@Slf4j
@Mapper(componentModel = "spring", uses = {CommissionMemberMapper.class, ApplicationAttachedDocMapper.class})
public abstract class ApplicationCommissionMemberStatementMapper extends BaseObjectMapper<ApplicationCommissionMemberStatementEntity, ApplicationCommissionMemberStatementDTO> {
    @Autowired
    private AbdocsUrlBuilder abdocsUrlBuilder;

    @AfterMapping
    public void afterToDto(ApplicationCommissionMemberStatementEntity source, @MappingTarget ApplicationCommissionMemberStatementDTO target) {

        if (Objects.nonNull(target.getAttachedDoc())) {
            String docflowId = target.getAttachedDoc().getDocflowId();
            if (StringUtils.hasText(docflowId)) {
                try {
                    target.getAttachedDoc().setAbdocsViewDocumentUrl(abdocsUrlBuilder.viewDocWithAuth(Integer.valueOf(docflowId)));
                } catch (Exception e) {
                    log.error("Cannot set abdocs view document url for attachment with docflow id = " + docflowId);
                }
            }
        }
    }
}
