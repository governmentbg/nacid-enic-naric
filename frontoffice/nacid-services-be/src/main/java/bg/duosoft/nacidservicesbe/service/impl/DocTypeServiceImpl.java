package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidcoreclient.client.cfgdoctype.CfgDocTypeClient;
import bg.duosoft.nacidcoreclient.client.cfgdoctyperequirement.CfgDocTypeRequirementClient;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgDocTypeDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgDocTypeRequirementDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocTypeDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import bg.duosoft.nacidservicesbe.service.DocTypeService;
import bg.duosoft.nacidservicesbe.service.ExpressionEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.04.2023
 * Time: 17:14
 */
@Service
@RequiredArgsConstructor
public class DocTypeServiceImpl implements DocTypeService {

    private final CfgDocTypeRequirementClient cfgDocTypeRequirementClient;
    private final CfgDocTypeClient cfgDocTypeClient;
    private final ExpressionEvaluationService expressionEvaluationService;

    @Override
    public List<DocTypeDTO> getApplicationDocTypes(CommonApplicationDTO application){
        List<CfgDocTypeDTO> docTypeConfigs = cfgDocTypeClient.getAllByAppTypeAndSubtype(application.getApplicationType(), application.getApplicationSubtype());
        if(docTypeConfigs != null){
            return docTypeConfigs.stream().filter(
                    c -> Boolean.TRUE.equals(c.getDocType().getIsActive()) &&
                    expressionEvaluationService.isExpressionValidForApplication(application, c.getShowExpression())
            ).map(c -> c.getDocType()).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<CfgDocTypeRequirementDTO> getApplicationDocTypeRequirements(CommonApplicationDTO application){
        List<CfgDocTypeRequirementDTO> requirements = cfgDocTypeRequirementClient.getAllByAppTypeAndSubtype(application.getApplicationType(), application.getApplicationSubtype());
        if(requirements != null){
            return requirements.stream().filter(
                    c -> Boolean.TRUE.equals(c.getDocType().getIsActive()) &&
                    expressionEvaluationService.isExpressionValidForApplication(application, c.getRequirementExpression())
            ).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

}
