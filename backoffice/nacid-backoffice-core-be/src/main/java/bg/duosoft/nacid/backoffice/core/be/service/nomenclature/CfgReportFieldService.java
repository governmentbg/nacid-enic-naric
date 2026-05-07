package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgReportFieldRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgReportFieldDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReportFieldMapper;
import bg.duosoft.nacidshared.web.service.impl.CrudServiceBaseImpl;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class CfgReportFieldService extends CrudServiceBaseImpl<String, CfgReportFieldDTO> {
    private final CfgReportFieldRepository fieldRepository;
    private final ReportFieldMapper reportFieldMapper;


    @Override
    protected CfgReportFieldRepository getRepository() {
        return fieldRepository;
    }

    @Override
    protected ReportFieldMapper getMapper() {
        return reportFieldMapper;
    }

    @Override
    protected Validator<CfgReportFieldDTO> getValidator() {
        return null;
    }

    public boolean isFieldExist(String code, String sqlCode){
        Integer count = fieldRepository.countFieldsWithExcludedSqlCode(code, sqlCode);
        return !(Objects.isNull(count) || 0 == count);
    }
}
