package bg.duosoft.nacidservicesbe.mapper.officialnotes;

import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidcoredata.mapper.FoApplicationStatusMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.CountryMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNotesApplicationDTO;
import bg.duosoft.nacidservicesbe.domain.entity.lib.OfficialNoteFullEntity;
import bg.duosoft.nacidservicesbe.mapper.base.BaseApplicationObjectMapper;
import bg.duosoft.nacidservicesbe.mapper.common.applicantdetails.CommonApplicantDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.common.application.AppStatusHistoryMapper;
import bg.duosoft.nacidservicesbe.mapper.common.application.ApplicationMultipleRecordMapper;
import bg.duosoft.nacidservicesbe.mapper.common.document.ApplicationReceiptMapper;
import bg.duosoft.nacidservicesbe.mapper.common.document.AttachedDocumentMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.02.2023
 * Time: 14:20
 */
@Mapper(componentModel = "spring", uses = {
        CountryMapper.class,
        OfficialNotesDetailsMapper.class,
        CommonApplicantDetailsMapper.class,
        AttachedDocumentMapper.class,
        ApplicationReceiptMapper.class,
        IntegerToBooleanMapper.class,
        AppStatusHistoryMapper.class,
        FoApplicationStatusMapper.class,
        ApplicationTypeMapper.class,
        ApplicationSubtypeMapper.class,
        ApplicationMultipleRecordMapper.class
})
public abstract class OfficialNotesApplicationMapper extends BaseApplicationObjectMapper<OfficialNoteFullEntity, OfficialNotesApplicationDTO> {

    @Autowired
    private ReferenceDataMapper referenceDataMapper;

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "application", source = "applicantDetails")
    @Mapping(target = "officialNoteDetails", source = "officialNotesDetails.officialNotesKinds")
    @Mapping(target = "detailedInformation", source = "officialNotesDetails.additionalInformation")
    public abstract OfficialNoteFullEntity toEntity(OfficialNotesApplicationDTO application);

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
    @Mapping(target = "appsFromMultiple", source = "application.multipleApplication.applications")

    @Mapping(target = "applicationType", source = "application.applicationTypeCode")
    @Mapping(target = "applicationSubtype", source = "application.applicationSubtype.id")
    @Mapping(target = "applicationSubtypeName", source = "application.applicationSubtype.name")
    @Mapping(target = "tempNumber", source = "application.tempNumber")
    @Mapping(target = "documentDetails.attachments", source = "application.attachedDocs")
    @Mapping(target = "receipts", source = "application.receipts")
    public abstract OfficialNotesApplicationDTO toDto(OfficialNoteFullEntity officialNoteEntity);

    @AfterMapping
    public void afterToEntity(@MappingTarget OfficialNoteFullEntity target, OfficialNotesApplicationDTO source) {
        afterToApplicationEntity(target, source);
        if (source.getOfficialNotesDetails() != null && source.getOfficialNotesDetails().getServiceType() != null && source.getOfficialNotesDetails().getServiceType().getId() != null && target.getApplication() != null) {
            target.getApplication().setServiceType(referenceDataMapper.toEntity(source.getOfficialNotesDetails().getServiceType()));
        }
    }

    @AfterMapping
    public void afterToDto(@MappingTarget OfficialNotesApplicationDTO target, OfficialNoteFullEntity source) {
        if (source.getApplication() != null && source.getApplication().getServiceType() != null && source.getApplication().getServiceType().getPk() != null && target.getOfficialNotesDetails() != null) {
            target.getOfficialNotesDetails().setServiceType(referenceDataMapper.toDto(source.getApplication().getServiceType()));
        }
    }

}
