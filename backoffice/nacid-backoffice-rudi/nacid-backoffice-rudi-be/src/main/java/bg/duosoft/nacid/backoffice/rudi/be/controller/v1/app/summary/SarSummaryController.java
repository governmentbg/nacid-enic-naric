package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.summary;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationStatusHistoryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.SarApplicationType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgSarAppStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.SarApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.summary.SarFlagColorCodeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.summary.SarSummaryDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.CommonUtils;
import bg.duosoft.nacid.backoffice.core.data.util.common.PersonUtils;
import bg.duosoft.nacid.backoffice.rudi.be.service.AppStatusClientService;
import bg.duosoft.nacid.backoffice.rudi.be.service.ReferenceDataClientService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.SUMMARY)
@RequestMapping("/api/v1/applications/summary/sar")
public class SarSummaryController extends BaseSummaryController {

    private final ReferenceDataClientService referenceDataClientService;
    private final AppStatusClientService appStatusClientService;

    @GetMapping(value = "/{applicationId}")
    @ApiOperation(value = "Get sar summary")
    public SarSummaryDTO getSummary(@PathVariable Integer applicationId) {
        RudiApplicationDTO app = selectApplicationById(applicationId);

        SarSummaryDTO summary = new SarSummaryDTO();
        setBaseSummary(summary, app);
        summary.setDiplomaOwner(getDiplomaOwnerName(app));
        summary.setSarFlagColorCode(getSarFlagColorCode(app));

        return summary;
    }

    private static String getDiplomaOwnerName(RudiApplicationDTO rudiApplicationDTO) {
        TrainingCourseDTO trainingCourse = rudiApplicationDTO.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            PersonDTO diplomaOwner = trainingCourse.getDiplomaOwner();
            if (Objects.nonNull(diplomaOwner)) {
                return PersonUtils.getPersonName(diplomaOwner);
            }
        }
        return null;
    }

    private SarFlagColorCodeDTO getSarFlagColorCode(RudiApplicationDTO rudiApplicationDTO) {
        SarFlagColorCodeDTO sarFlagColorCode = SarFlagColorCodeDTO.newInstance();
        setSarFlagNames(sarFlagColorCode, rudiApplicationDTO);
        setSarFlagColors(sarFlagColorCode, rudiApplicationDTO);
        return sarFlagColorCode;
    }

    private void setSarFlagNames(SarFlagColorCodeDTO sarFlagColorCode, RudiApplicationDTO rudiApplicationDTO) {
        List<ReferenceDataDTO> nomRecords = null;

        try {
            nomRecords = referenceDataClientService.selectAllByDomain(ReferenceDataDomain.SAR_APPLICATION_TYPE);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }

        if (!CollectionUtils.isEmpty(nomRecords)) {
            Map<String, String> map = nomRecords.stream().collect(Collectors.toMap(ReferenceDataDTO::getId, ReferenceDataDTO::getName));

            SarApplicationDTO sarApplication = rudiApplicationDTO.getSarApplication();
            if (Objects.nonNull(sarApplication)) {
                if (BooleanUtils.isTrue(sarApplication.getIsStatute())) {
                    sarFlagColorCode.getStatute().setName(map.get(SarApplicationType.STATUTE.code()));
                }
                if (BooleanUtils.isTrue(sarApplication.getIsAuthenticity())) {
                    sarFlagColorCode.getAuthenticity().setName(map.get(SarApplicationType.AUTHENTICITY.code()));
                }
                if (BooleanUtils.isTrue(sarApplication.getIsRecommendation())) {
                    sarFlagColorCode.getRecommendation().setName(map.get(SarApplicationType.RECOMMENDATION.code()));
                }
            }

        }
    }

    private void setSarFlagColors(SarFlagColorCodeDTO sarFlagColorCode, RudiApplicationDTO rudiApplicationDTO) {
        SarApplicationDTO sarApplication = rudiApplicationDTO.getSarApplication();
        if (Objects.nonNull(sarApplication)) {
            List<CfgSarAppStatusDTO> cfgSarAppStatusDTOS = appStatusClientService.selectAllSarStatusConfigs();
            if (CollectionUtils.isEmpty(cfgSarAppStatusDTOS)) {
                return;
            }

            Map<String, Boolean> map = cfgSarAppStatusDTOS.stream().filter(o -> !CommonUtils.isEmpty(o.getSarApplicationType())).filter(o -> !CommonUtils.isEmpty(o.getStatus())).collect(Collectors.toMap(x -> x.getSarApplicationType().getId() + "-" + x.getStatus().getId(), CfgSarAppStatusDTO::getIsPositive));

            if (BooleanUtils.isTrue(sarApplication.getIsStatute())) {
                SarFlagColorCodeDTO.FlagData statute = sarFlagColorCode.getStatute();
                setColor(sarApplication.getStatuteFinalStatus(), map, statute, SarApplicationType.STATUTE);
                if (SarFlagColorCodeDTO.Color.RED == statute.getColor()) {
                    sarFlagColorCode.getAuthenticity().setColor(SarFlagColorCodeDTO.Color.GREY);
                    sarFlagColorCode.getRecommendation().setColor(SarFlagColorCodeDTO.Color.GREY);
                    return;
                }
            }

            if (BooleanUtils.isTrue(sarApplication.getIsAuthenticity())) {
                SarFlagColorCodeDTO.FlagData authenticity = sarFlagColorCode.getAuthenticity();
                setColor(sarApplication.getAuthenticityFinalStatus(), map, authenticity, SarApplicationType.AUTHENTICITY);
                if (SarFlagColorCodeDTO.Color.RED == authenticity.getColor()) {
                    sarFlagColorCode.getRecommendation().setColor(SarFlagColorCodeDTO.Color.GREY);
                    return;
                }
            }

            if (BooleanUtils.isTrue(sarApplication.getIsRecommendation())) {
                SarFlagColorCodeDTO.FlagData recommendation = sarFlagColorCode.getRecommendation();
                setColor(sarApplication.getRecommendationFinalStatus(), map, recommendation, SarApplicationType.RECOMMENDATION);
            }
        }
    }

    private static void setColor(ApplicationStatusHistoryDTO statuteFinalStatus, Map<String, Boolean> map, SarFlagColorCodeDTO.FlagData statute, SarApplicationType type) {
        if (Objects.isNull(statuteFinalStatus) || CommonUtils.isEmpty(statuteFinalStatus.getStatus())) {
            statute.setColor(SarFlagColorCodeDTO.Color.BLUE);
        } else {
            String statusCode = statuteFinalStatus.getStatus().getId();
            Boolean isPositive = map.get(type.code() + "-" + statusCode);
            if (BooleanUtils.isTrue(isPositive)) {
                statute.setColor(SarFlagColorCodeDTO.Color.GREEN);
            }
            if (BooleanUtils.isFalse(isPositive)) {
                statute.setColor(SarFlagColorCodeDTO.Color.RED);
            }
        }
    }

}
