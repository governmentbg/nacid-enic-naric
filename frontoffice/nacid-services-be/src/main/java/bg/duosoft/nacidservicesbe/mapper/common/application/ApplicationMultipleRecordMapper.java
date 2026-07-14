package bg.duosoft.nacidservicesbe.mapper.common.application;

import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.ApplicationTypeMapper;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationMultipleRecordDTO;
import bg.duosoft.nacidfrontofficedto.services.inquiry.InquiryKind;
import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNoteKind;
import bg.duosoft.nacidservicesbe.domain.entity.common.projection.ApplicationMultipleProjectionEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.04.2023
 * Time: 14:22
 */
@Mapper(componentModel = "spring", uses = {
        ApplicationTypeMapper.class,
        ApplicationSubtypeMapper.class,
        IntegerToBooleanMapper.class
})
public abstract class ApplicationMultipleRecordMapper extends BaseObjectMapper<ApplicationMultipleProjectionEntity, ApplicationMultipleRecordDTO> {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "applicationType", source = "applicationTypeCode")
    @Mapping(target = "applicationSubtype", source = "applicationSubtypeCode")
    @Mapping(target = "nacidSearch", source = "biblioReference.searchBgFlag")
    @Mapping(target = "foreignSearch", source = "biblioReference.searchForeignFlag")
    @Mapping(target = "multipleApplicationId", source = "multipleApplication.id")
    public abstract ApplicationMultipleRecordDTO toDto(ApplicationMultipleProjectionEntity applicationMultipleProjectionEntity);

    @AfterMapping
    public void afterToDto(@MappingTarget ApplicationMultipleRecordDTO target, ApplicationMultipleProjectionEntity source){
        if(source.getOfficialNotesDetails() != null && source.getOfficialNotesDetails().size() == 1){
            target.setOfficialNoteKind(OfficialNoteKind.fromCode(source.getOfficialNotesDetails().get(0).getOfficialNoteKindCode()));
        }
        if(source.getInquiryKinds() != null && source.getInquiryKinds().size() == 1){
            target.setInquiryKind(InquiryKind.fromCode(source.getInquiryKinds().get(0).getInquiryKindCode()));
        }
    }
}
