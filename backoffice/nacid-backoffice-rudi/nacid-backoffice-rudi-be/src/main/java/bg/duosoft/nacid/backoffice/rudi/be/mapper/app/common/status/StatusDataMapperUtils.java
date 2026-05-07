package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationStatusType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.DocflowStatusType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LegalReasonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ProfGroupDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationRecognizedDetailsDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.UdirecDocrecStatusDataCommonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.mandatory.RudiMandatoryStatusData;
import org.mapstruct.MappingTarget;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class StatusDataMapperUtils {

    public static void afterToMandatoryStatusDataDto(RudiApplicationDTO source, @MappingTarget RudiMandatoryStatusData target) {
        if (Objects.nonNull(source.getApplication())) {
            ApplicationDTO application = source.getApplication();
            if (StringUtils.hasText(application.getArchiveNumber())) {
                target.setArchiveNumber(application.getArchiveNumber());
            }
        }

    }

    public static void afterOverrideMandatoryStatusData(RudiMandatoryStatusData source, @MappingTarget RudiApplicationDTO target) {
        if (Objects.isNull(source.getLegalReason()) || Objects.isNull(source.getLegalReason().getId())) {
            target.setLegalReason(null);
        }

        Integer currentLegalReason = Objects.nonNull(target.getLegalReason()) ? target.getLegalReason().getId() : null;
        Integer insertLegalReason = Objects.nonNull(source.getLegalReason()) ? source.getLegalReason().getId() : null;
        if (!Objects.equals(currentLegalReason, insertLegalReason)) {
            target.setLegalReason(Objects.nonNull(insertLegalReason) ? new LegalReasonDTO(insertLegalReason) : null);
        }

        if (ApplicationStatusType.SUSPEND_SUBMITTED_DOCUMENTS.code().equals(target.getApplication().getStatus().getId())) {
            target.setSubmittedDocs(source.getSubmittedDocs());
        }

        if (DocflowStatusType.ARCHIVED.code().equals(target.getApplication().getDocflowStatus().getId())) {
            target.getApplication().setArchiveNumber(source.getArchiveNumber());
        }
    }
    public static void afterOverrideUdirecDocrecStatusData(UdirecDocrecStatusDataCommonDTO source, @MappingTarget RudiApplicationDTO target) {
        if (Objects.nonNull(source.getRecognizedProfGroupId())) {
            if (Objects.isNull(target.getApplicationRecognizedDetails())) {
                target.setApplicationRecognizedDetails(new ApplicationRecognizedDetailsDTO());
            }
            target.getApplicationRecognizedDetails().setProfGroup(new ProfGroupDTO(source.getRecognizedProfGroupId()));
        }
    }

    public static void afterToUdirecDocrecStatusDataSection(RudiApplicationDTO source, @MappingTarget UdirecDocrecStatusDataCommonDTO target) {
        if (Objects.nonNull(source.getApplicationRecognizedDetails()) && Objects.nonNull(source.getApplicationRecognizedDetails().getProfGroup())) {
            target.setRecognizedProfGroupId(source.getApplicationRecognizedDetails().getProfGroup().getId());
        }
    }
}
