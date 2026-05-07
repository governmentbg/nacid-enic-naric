package bg.duosoft.nacidservicesbe.mapper.unichecks;

import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidcoredata.mapper.FoApplicationStatusMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.CountryMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksEducationDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiApplicationFullEntity;
import bg.duosoft.nacidservicesbe.mapper.base.BaseApplicationObjectMapper;
import bg.duosoft.nacidservicesbe.mapper.common.applicantdetails.CommonApplicantDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.common.application.AppStatusHistoryMapper;
import bg.duosoft.nacidservicesbe.mapper.common.document.ApplicationReceiptMapper;
import bg.duosoft.nacidservicesbe.mapper.common.document.AttachedDocumentMapper;
import bg.duosoft.nacidservicesbe.mapper.common.education.SpecialityMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.01.2023
 * Time: 15:57
 */
@Mapper(componentModel = "spring", uses = {
        SpecialityMapper.class,
        CountryMapper.class,
        UniChecksEducationDetailsMapper.class,
        CommonApplicantDetailsMapper.class,
        AttachedDocumentMapper.class,
        ApplicationReceiptMapper.class,
        IntegerToBooleanMapper.class,
        SarApplicationMapper.class,
        AppStatusHistoryMapper.class,
        FoApplicationStatusMapper.class,
        ApplicationTypeMapper.class,
        ApplicationSubtypeMapper.class
})
public abstract class UniChecksApplicationMapper extends BaseApplicationObjectMapper<RudiApplicationFullEntity, UniChecksApplicationDTO> {

    @Autowired
    private SarApplicationMapper sarApplicationMapper;

    @Autowired
    private ReferenceDataMapper referenceDataMapper;

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "application", source = "applicantDetails")
    @Mapping(target = "trainingCourses", source = "educationDetails")
    @Mapping(target = "sarApplication", source = "educationDetails")
    public abstract RudiApplicationFullEntity toEntity(UniChecksApplicationDTO application);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "dateCreated", source = "application.dateCreated")
    @Mapping(target = "userCreated", source = "application.userCreated")
    @Mapping(target = "entryNumber", source = "application.entryNumber")
    @Mapping(target = "entryDate", source = "application.entryDate")
    @Mapping(target = "lastSubmissionDate", source = "application.lastSubmissionDate")
    @Mapping(target = "foStatus", source = "application.foStatusCode")
    @Mapping(target = "lastStatusName", source = "application.lastStatusName")
    @Mapping(target = "accessCode", source = "application.accessCode")
    @Mapping(target = "educationDetails", source = "trainingCourses")
    @Mapping(target = "statusHistory", source = "application.statusHistory")

    @Mapping(target = "applicationType", source = "application.applicationTypeCode")
    @Mapping(target = "applicationSubtype", source = "application.applicationSubtype.id")
    @Mapping(target = "applicationSubtypeName", source = "application.applicationSubtype.name")
    @Mapping(target = "tempNumber", source = "application.tempNumber")
    @Mapping(target = "documentDetails.attachments", source = "application.attachedDocs")
    @Mapping(target = "receipts", source = "application.receipts")
    public abstract UniChecksApplicationDTO toDto(RudiApplicationFullEntity rudiApplicationFullEntity);

    @AfterMapping
    public void afterToEntity(@MappingTarget RudiApplicationFullEntity target, UniChecksApplicationDTO source){
        afterToApplicationEntity(target, source);
        if(source.getEducationDetails() != null && source.getEducationDetails().getServiceType() != null && source.getEducationDetails().getServiceType().getId() != null && target.getApplication() != null){
            target.getApplication().setServiceType(referenceDataMapper.toEntity(source.getEducationDetails().getServiceType()));
        }
    }

    @AfterMapping
    public void afterToDto(@MappingTarget UniChecksApplicationDTO target, RudiApplicationFullEntity source){
        if(source.getSarApplication() != null){
            UniChecksEducationDetailsDTO sarDetailsDto = sarApplicationMapper.toDto(source.getSarApplication());
            SarApplicationMapper.setSarDetailsToMainEducationDetailsDto(target.getEducationDetails(), sarDetailsDto);
        }
        if(source.getApplication() != null && source.getApplication().getServiceType() != null && source.getApplication().getServiceType().getPk() != null && target.getEducationDetails() != null){
            target.getEducationDetails().setServiceType(referenceDataMapper.toDto(source.getApplication().getServiceType()));
        }
    }

}
