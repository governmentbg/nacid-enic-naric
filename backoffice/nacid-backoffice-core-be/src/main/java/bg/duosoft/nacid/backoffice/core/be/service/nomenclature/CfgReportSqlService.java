package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.SqlRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgReportFieldRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgReportSqlRepository;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.CfgReportSqlValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgReportFieldEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgReportSqlEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataCode;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgReportFieldDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgReportSqlDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CfgReportSqlFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReportFieldMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReportSqlMapper;
import bg.duosoft.nacidshared.web.service.impl.CrudServiceBaseImpl;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * User: ggeorgiev
 * Date: 04.11.2022
 * Time: 14:30
 */
@Service
@RequiredArgsConstructor
public class CfgReportSqlService extends CrudServiceBaseImpl<String, CfgReportSqlDTO> {
    private final ReportSqlMapper mapper;
    private final CfgReportSqlRepository repository;
    private final CfgReportFieldRepository fieldRepository;
    private final SqlRepository sqlRepository;
    private final ReportFieldMapper reportFieldMapper;

    private final CfgReportSqlValidator validator;

    @Override
    protected CfgReportSqlRepository getRepository() {
        return repository;
    }

    @Override
    protected ReportSqlMapper getMapper() {
        return mapper;
    }

    public List<CfgReportSqlDTO> selectReportSqlData(CfgReportSqlFilterDTO filter) {
        List<CfgReportSqlEntity> result = repository.selectReportSqlData(filter);
        return mapper.toDtoList(result);
    }

    public int selectReportSqlDataCount(CfgReportSqlFilterDTO filter) {
        return repository.selectReportSqlDataCount(filter);
    }

    public List<String> getSqlParameterNames(String sql) {
        return sqlRepository.getSqlParameterNames(sql);
    }

    public List<CfgReportFieldDTO> getSqlColumnsBySql(String sql, Map<String, Object> params) {
        List<Map<String, Object>> res = sqlRepository.selectRowsAsMap(sql, params);

        List<String> columns = res.get(0).keySet().stream().sorted().toList();
        Map<String, CfgReportFieldEntity> fields = fieldRepository.findAllById(columns).stream().collect(Collectors.toMap(CfgReportFieldEntity::getId, Function.identity()));
        List<CfgReportFieldDTO> result = new ArrayList<>();
        columns.stream().filter(c -> !fields.containsKey(c)).map(c -> toCfgReportFieldDTO(c, null)).forEach(result::add);//adding the missing columns first
        columns.stream().filter(c -> fields.containsKey(c)).map(c -> toCfgReportFieldDTO(c, fields.get(c))).forEach(result::add);//adding the existing columns

        return result;
    }

    private CfgReportFieldDTO toCfgReportFieldDTO(String columnName, CfgReportFieldEntity entity) {
        if (entity != null) {
            return reportFieldMapper.toDto(entity);
        } else {
            return new CfgReportFieldDTO(columnName, null, new ReferenceDataDTO(ReferenceDataDomain.REPORT_FIELD_TYPE.domain(), ReferenceDataCode.REPORT_FIELD_TYPE_TEXT.code()));
        }
    }

    public List<CfgReportFieldDTO> getSqlColumnsByReportSqlId(String id, Map<String, Object> params) {
        String sql = repository.findById(id).orElseThrow().getSqlExpression();
        return getSqlColumnsBySql(sql, params);
    }

    @Override
    protected Validator<CfgReportSqlDTO> getValidator() {
        return validator;
    }

    @Override
    protected void beforeCreateOrUpdate(CfgReportSqlDTO cfgReportSqlDTO) {
        cfgReportSqlDTO.setDateUpdated(LocalDateTime.now());
    }
}
