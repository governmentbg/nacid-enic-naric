package bg.duosoft.nacidservicesbe.mapper.regprof;

import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidcoredata.mapper.FoApplicationStatusMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApplicationDTO;
import bg.duosoft.nacidservicesbe.domain.entity.regprof.RegprofApplicationFullEntity;
import bg.duosoft.nacidservicesbe.mapper.base.BaseApplicationObjectMapper;
import bg.duosoft.nacidservicesbe.mapper.common.application.AppStatusHistoryMapper;
import bg.duosoft.nacidservicesbe.mapper.common.document.ApplicationReceiptMapper;
import bg.duosoft.nacidservicesbe.mapper.common.document.AttachedDocumentMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.12.2022
 * Time: 11:33
 */
@Mapper(componentModel = "spring", uses = {
        IntegerToBooleanMapper.class,
        AttachedDocumentMapper.class,
        RegprofApplicantDetailsMapper.class,
        RegprofEducationDetailsMapper.class,
        ApplicationReceiptMapper.class,
        AppStatusHistoryMapper.class,
        FoApplicationStatusMapper.class,
        ApplicationTypeMapper.class,
        ApplicationSubtypeMapper.class
})
public abstract class RegprofApplicationMapper extends BaseApplicationObjectMapper<RegprofApplicationFullEntity, RegprofApplicationDTO> {

    @Autowired
    private ReferenceDataMapper referenceDataMapper;

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "application", source = "applicantDetails")
    @Mapping(target = "trainingAndExperience", source = "educationDetails")
    public abstract RegprofApplicationFullEntity toEntity(RegprofApplicationDTO application);

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
    @Mapping(target = "apostilleApplication", source = "apostilleApplicationFlag")
    @Mapping(target = "externalSystemId", source = "application.externalSystemId")
    @Mapping(target = "externalSystemDocumentId", source = "application.externalSystemDocumentId")
    public abstract RegprofApplicationDTO toDto(RegprofApplicationFullEntity regprofApplicationFullEntity);

    @AfterMapping
    public void afterToEntity(@MappingTarget RegprofApplicationFullEntity target, RegprofApplicationDTO source){
        afterToApplicationEntity(target, source);
        if(source.getEducationDetails() != null && source.getEducationDetails().getServiceType() != null && source.getEducationDetails().getServiceType().getId() != null && target.getApplication() != null){
            target.getApplication().setServiceType(referenceDataMapper.toEntity(source.getEducationDetails().getServiceType()));
        }
    }

    @AfterMapping
    public void afterToDto(@MappingTarget RegprofApplicationDTO target, RegprofApplicationFullEntity source ){
        if(source.getApplication() != null && source.getApplication().getServiceType() != null && source.getApplication().getServiceType().getPk() != null && target.getEducationDetails() != null){
            target.getEducationDetails().setServiceType(referenceDataMapper.toDto(source.getApplication().getServiceType()));
        }
    }
}
