package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgReportFieldEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgReportSqlEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgReportFieldDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgReportSqlDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

/**
 * User: ggeorgiev
 * Date: 04.11.2022
 * Time: 14:27
 */
@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, ReferenceDataMapper.class})
public abstract class ReportFieldMapper extends BaseObjectMapper<CfgReportFieldEntity, CfgReportFieldDTO> {
}
