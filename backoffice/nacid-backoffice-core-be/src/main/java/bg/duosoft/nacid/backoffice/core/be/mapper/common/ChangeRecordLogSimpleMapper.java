package bg.duosoft.nacid.backoffice.core.be.mapper.common;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.DictionaryService;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ChangeRecordLogEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ChangeRecordLogSimpleDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DictionaryDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class ChangeRecordLogSimpleMapper extends BaseObjectMapper<ChangeRecordLogEntity, ChangeRecordLogSimpleDTO> {

    @Autowired
    private DictionaryService dictionaryService;

    @AfterMapping
    protected void afterToDto(ChangeRecordLogEntity changeRecordLogEntity, @MappingTarget ChangeRecordLogSimpleDTO changeRecordLogSimple) {
        String originalServiceName = changeRecordLogEntity.getService();
        if (StringUtils.hasText(originalServiceName)) {
            DictionaryDTO dictionaryRecord = dictionaryService.selectById(originalServiceName);
            if (Objects.nonNull(dictionaryRecord)) {
                changeRecordLogSimple.setServiceName(dictionaryRecord.getName());
            } else {
                changeRecordLogSimple.setServiceName(originalServiceName);
            }
        }
    }
}
