package bg.duosoft.nacidservicesbe.mapper.suggestion;

import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidcoredata.mapper.FoApplicationStatusMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.CountryMapper;
import bg.duosoft.nacidfrontofficedto.services.suggestion.SuggestionApplicationDTO;
import bg.duosoft.nacidservicesbe.domain.entity.lib.SuggestionFullEntity;
import bg.duosoft.nacidservicesbe.mapper.base.BaseApplicationObjectMapper;
import bg.duosoft.nacidservicesbe.mapper.common.applicantdetails.CommonApplicantDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.common.application.AppStatusHistoryMapper;
import bg.duosoft.nacidservicesbe.mapper.common.document.ApplicationReceiptMapper;
import bg.duosoft.nacidservicesbe.mapper.common.document.AttachedDocumentMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.03.2023
 * Time: 16:50
 */
@Mapper(componentModel = "spring", uses = {
        CountryMapper.class,
        CommonApplicantDetailsMapper.class,
        AttachedDocumentMapper.class,
        ApplicationReceiptMapper.class,
        IntegerToBooleanMapper.class,
        AppStatusHistoryMapper.class,
        FoApplicationStatusMapper.class,
        ApplicationTypeMapper.class,
        ApplicationSubtypeMapper.class
})
public abstract class SuggestionApplicationMapper extends BaseApplicationObjectMapper<SuggestionFullEntity, SuggestionApplicationDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "application", source = "applicantDetails")
    @Mapping(target = "suggestionText", source = "suggestionDetails.suggestion")
    public abstract SuggestionFullEntity toEntity(SuggestionApplicationDTO suggestionApplicationDTO);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "dateCreated", source = "application.dateCreated")
    @Mapping(target = "userCreated", source = "application.userCreated")
    @Mapping(target = "entryNumber", source = "application.entryNumber")
    @Mapping(target = "entryDate", source = "application.entryDate")
    @Mapping(target = "lastSubmissionDate", source = "application.lastSubmissionDate")
    @Mapping(target = "foStatus", source = "application.foStatusCode")
    @Mapping(target = "lastStatusName", source = "application.lastStatusName")
    @Mapping(target = "accessCode", source = "application.accessCode")
    @Mapping(target = "statusHistory", source = "application.statusHistory")

    @Mapping(target = "applicationType", source = "application.applicationTypeCode")
    @Mapping(target = "applicationSubtype", source = "application.applicationSubtype.id")
    @Mapping(target = "applicationSubtypeName", source = "application.applicationSubtype.name")
    @Mapping(target = "tempNumber", source = "application.tempNumber")
    @Mapping(target = "documentDetails.attachments", source = "application.attachedDocs")
    @Mapping(target = "receipts", source = "application.receipts")
    public abstract SuggestionApplicationDTO toDto(SuggestionFullEntity suggestionEntity);

    @AfterMapping
    public void afterToEntity(@MappingTarget SuggestionFullEntity target, SuggestionApplicationDTO source){
        afterToApplicationEntity(target, source);
    }
}
