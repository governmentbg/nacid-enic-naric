package bg.duosoft.nacidservicesbe.mapper.publicaccess;

import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidcoredata.mapper.FoApplicationStatusMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.CountryMapper;
import bg.duosoft.nacidfrontofficedto.services.publicaccess.PublicAccessApplicationDTO;
import bg.duosoft.nacidservicesbe.domain.entity.lib.PublicAccessFullEntity;
import bg.duosoft.nacidservicesbe.mapper.base.BaseApplicationObjectMapper;
import bg.duosoft.nacidservicesbe.mapper.common.applicantdetails.CommonApplicantDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.common.application.AppStatusHistoryMapper;
import bg.duosoft.nacidservicesbe.mapper.common.document.ApplicationReceiptMapper;
import bg.duosoft.nacidservicesbe.mapper.common.document.AttachedDocumentMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.08.2023
 * Time: 15:33
 */
@Mapper(componentModel = "spring", uses = {
        CountryMapper.class,
        PublicAccessDetailsMapper.class,
        CommonApplicantDetailsMapper.class,
        AttachedDocumentMapper.class,
        ApplicationReceiptMapper.class,
        IntegerToBooleanMapper.class,
        AppStatusHistoryMapper.class,
        FoApplicationStatusMapper.class,
        ApplicationTypeMapper.class,
        ApplicationSubtypeMapper.class
})
public abstract class PublicAccessApplicationMapper extends BaseApplicationObjectMapper<PublicAccessFullEntity, PublicAccessApplicationDTO> {

    @Autowired
    private PublicAccessDetailsMapper publicAccessDetailsMapper;

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "application", source = "applicantDetails")
    public abstract PublicAccessFullEntity toEntity(PublicAccessApplicationDTO publicAccessApplicationDTO);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "publicAccessDetails", source = ".")
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
    public abstract PublicAccessApplicationDTO toDto(PublicAccessFullEntity publicAccessFullEntity);

    @AfterMapping
    public void afterToEntity(@MappingTarget PublicAccessFullEntity target, PublicAccessApplicationDTO source){
        afterToApplicationEntity(target, source);
        if(source.getPublicAccessDetails() != null){
            PublicAccessFullEntity mappedDetails = publicAccessDetailsMapper.toEntity(source.getPublicAccessDetails());
            PublicAccessDetailsMapper.copyDetailsToApplication(target, mappedDetails);
        }
    }
}
