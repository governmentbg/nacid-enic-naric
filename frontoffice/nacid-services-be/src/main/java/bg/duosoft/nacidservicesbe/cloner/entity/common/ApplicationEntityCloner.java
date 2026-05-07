package bg.duosoft.nacidservicesbe.cloner.entity.common;

import bg.duosoft.nacidservicesbe.cloner.entity.base.BaseCloner;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.04.2023
 * Time: 14:37
 */
@Mapper(componentModel = "spring", uses = {
        AppStatusHistoryEntityCloner.class
})
public abstract class ApplicationEntityCloner extends BaseCloner<ApplicationEntity> {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tempNumber", ignore = true)
    @Mapping(target = "applicant.id", ignore = true)
    @Mapping(target = "representative.id", ignore = true)
    @Mapping(target = "contactAddress.id", ignore = true)
    @Mapping(target = "documentRecipientAddress.id", ignore = true)
    @Mapping(target = "diffDiplomaNames", ignore = true)
    @Mapping(target = "attachedDocs", ignore = true)
    @Mapping(target = "receipts", ignore = true)
    @Mapping(target = "original", constant = "false")
    @Mapping(target = "sortedHistoryStream", ignore = true)
    @Mapping(target = "foStatusCode", ignore = true)
    @Mapping(target = "lastStatusName", ignore = true)
    @Mapping(target = "lastSubmissionDate", ignore = true)
    @Mapping(target = "applicationDocumentReceiveMethods", ignore = true)
    public abstract ApplicationEntity clone(ApplicationEntity source);
}
