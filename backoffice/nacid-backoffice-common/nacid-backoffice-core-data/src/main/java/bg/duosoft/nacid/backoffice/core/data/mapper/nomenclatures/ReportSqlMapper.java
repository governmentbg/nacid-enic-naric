package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgReportSqlEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgReportSqlDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * User: ggeorgiev
 * Date: 04.11.2022
 * Time: 14:27
 */
@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, ReportFieldMapper.class})
public abstract class ReportSqlMapper extends BaseObjectMapper<CfgReportSqlEntity, CfgReportSqlDTO> {
    @AfterMapping
    protected void afterMapping(CfgReportSqlDTO source, @MappingTarget CfgReportSqlEntity target) {
        if (target.getFields() != null) {
            target.getFields().forEach(f -> f.setSql(target));
        }

    }
}
