package bg.duosoft.nacidservicesbe.mapper.regprofapostille;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ApplicationSubtypeEntity;
import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApostilleApplicationDTO;
import bg.duosoft.nacidservicesbe.domain.entity.regprof.RegprofApplicationFullEntity;
import bg.duosoft.nacidservicesbe.mapper.common.document.AttachedDocumentMapper;
import bg.duosoft.nacidservicesbe.mapper.regprof.RegprofApplicantDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.regprof.RegprofEducationDetailsMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.06.2023
 * Time: 15:32
 */
@Mapper(componentModel = "spring", uses = {
        RegprofApplicantDetailsMapper.class,
        RegprofEducationDetailsMapper.class,
        AttachedDocumentMapper.class,
        IntegerToBooleanMapper.class
})
public abstract class RegprofApostilleApplicationMapper extends BaseObjectMapper<RegprofApplicationFullEntity, RegprofApostilleApplicationDTO> {

    @Autowired
    private ReferenceDataMapper referenceDataMapper;

    @Autowired
    private ApplicationTypeMapper applicationTypeMapper;

    @Autowired
    private ApplicationSubtypeMapper applicationSubtypeMapper;

    @Autowired
    private AttachedDocumentMapper attachedDocumentMapper;

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "application", source = "applicantDetails")
    @Mapping(target = "trainingAndExperience", source = "educationDetails")
    public abstract RegprofApplicationFullEntity toEntity(RegprofApostilleApplicationDTO applicationDTO);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "dateCreated", source = "application.dateCreated")
    @Mapping(target = "lastSubmissionDate", source = "application.lastSubmissionDate")
    @Mapping(target = "tempNumber", source = "application.tempNumber")
    @Mapping(target = "entryNumber", source = "application.entryNumber")
    @Mapping(target = "entryDate", source = "application.entryDate")
    @Mapping(target = "documentDetails.attachments", source = "application.attachedDocs")
    @Mapping(target = "externalSystemId", source = "application.externalSystemId")
    @Mapping(target = "externalSystemDocumentId", source = "application.externalSystemDocumentId")
    @Mapping(target = "paid", source = "application.paidFlag")
    @Mapping(target = "ESigned", source = "application.signedFlag")
    public abstract RegprofApostilleApplicationDTO toDto(RegprofApplicationFullEntity regprofApplicationFullEntity);

    @AfterMapping
    public void afterToEntity(@MappingTarget RegprofApplicationFullEntity target, RegprofApostilleApplicationDTO source){
        target.setApostilleApplicationFlag(1);
        target.getApplication().setSignedFlag(Boolean.TRUE.equals(source.getESigned()) ? 1: 0);
        target.getApplication().setPaidFlag(Boolean.TRUE.equals(source.getPaid()) ? 1: 0);

        target.getApplication().setExternalSystemId(source.getExternalSystemId());
        target.getApplication().setExternalSystemDocumentId(source.getExternalSystemDocumentId());

        target.getApplication().setDateCreated(source.getDateCreated());

        target.getApplication().setApplicationTypeCode(applicationTypeMapper.toEntity(ApplicationType.REGULATED_PROFESSIONS));
        target.getApplication().setApplicationSubtype(new ApplicationSubtypeEntity(applicationSubtypeMapper.toEntity(ApplicationSubtype.REGULATED_PROFESSIONS), null, null, null));

        if(source.getEducationDetails() != null && source.getEducationDetails().getServiceType() != null && source.getEducationDetails().getServiceType().getId() != null && target.getApplication() != null){
            target.getApplication().setServiceType(referenceDataMapper.toEntity(source.getEducationDetails().getServiceType()));
        }
        if(source.getDocumentDetails() != null) {
            target.getApplication().setAttachedDocs(attachedDocumentMapper.toEntityList(source.getDocumentDetails().getAttachments()));
        }
    }

    @AfterMapping
    public void afterToDto(@MappingTarget RegprofApostilleApplicationDTO target, RegprofApplicationFullEntity source ){
        if(source.getApplication() != null && source.getApplication().getServiceType() != null && source.getApplication().getServiceType().getPk() != null && target.getEducationDetails() != null){
            target.getEducationDetails().setServiceType(referenceDataMapper.toDto(source.getApplication().getServiceType()));
        }
    }
}
