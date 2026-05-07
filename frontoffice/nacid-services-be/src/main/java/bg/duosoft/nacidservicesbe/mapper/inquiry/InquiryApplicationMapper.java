package bg.duosoft.nacidservicesbe.mapper.inquiry;

import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidcoredata.mapper.FoApplicationStatusMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.CountryMapper;
import bg.duosoft.nacidfrontofficedto.services.inquiry.InquiryApplicationDTO;
import bg.duosoft.nacidservicesbe.domain.entity.lib.InquiryFullEntity;
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
        InquiryDetailsMapper.class,
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
public abstract class InquiryApplicationMapper extends BaseApplicationObjectMapper<InquiryFullEntity, InquiryApplicationDTO> {

    @Autowired
    private InquiryDetailsMapper inquiryDetailsMapper;

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "application", source = "applicantDetails")
    public abstract InquiryFullEntity toEntity(InquiryApplicationDTO application);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "inquiryDetails", source = ".")
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
    public abstract InquiryApplicationDTO toDto(InquiryFullEntity officialNoteEntity);

    @AfterMapping
    public void afterToEntity(@MappingTarget InquiryFullEntity target, InquiryApplicationDTO source){
        afterToApplicationEntity(target, source);
        if(source != null && source.getInquiryDetails() != null){
            InquiryFullEntity mapped = inquiryDetailsMapper.toEntity(source.getInquiryDetails());
            InquiryDetailsMapper.copyDetailsToApplication(target, mapped);
        }
    }
}
