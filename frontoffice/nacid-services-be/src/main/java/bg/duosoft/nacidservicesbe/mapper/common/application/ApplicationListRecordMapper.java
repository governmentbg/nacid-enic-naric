package bg.duosoft.nacidservicesbe.mapper.common.application;

import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.FoApplicationStatusMapper;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationListRecordDTO;
import bg.duosoft.nacidfrontofficedto.services.inquiry.InquiryKind;
import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNoteKind;
import bg.duosoft.nacidservicesbe.domain.entity.common.VwApplicationEntity;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.12.2022
 * Time: 16:19
 */
@Mapper(componentModel = "spring", uses = {
        ApplicationSubtypeMapper.class,
        FoApplicationStatusMapper.class,
        IntegerToBooleanMapper.class
})
public abstract class ApplicationListRecordMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "dateCreated", source = "dateCreated")
    @Mapping(target = "userCreated", source = "userCreated")
    @Mapping(target = "lastSubmissionDate", source = "lastSubmissionDate")
    @Mapping(target = "tempNumber", source = "tempNumber")
    @Mapping(target = "entryNumber", source = "entryNumber")
    @Mapping(target = "entryDate", source = "entryDate")
    @Mapping(target = "applicationSubtype", source = "applicationSubtypeCode")
    @Mapping(target = "foStatus", source = "foStatusCode")
    @Mapping(target = "foStatusName", source = "foStatusName")
    @Mapping(target = "lastStatusName", source = "lastStatusName")
    @Mapping(target = "statute", source = "sarApplication.statuteFlag")
    @Mapping(target = "authenticity", source = "sarApplication.authenticityFlag")
    @Mapping(target = "recommendation", source = "sarApplication.recommendationFlag")
    @Mapping(target = "signed", source = "signedFlag")
    @Mapping(target = "paid", source = "paidFlag")
    @Mapping(target = "reverted", source = "revertedFlag")
    @Mapping(target = "applicantName", source = "applicantName")
    @Mapping(target = "externalSystemId", source = "externalSystemId")
    @Mapping(target = "nacidSearch", source = "biblioReference.searchBgFlag")
    @Mapping(target = "foreignSearch", source = "biblioReference.searchForeignFlag")
    @Mapping(target = "notesCount", source = "notesCount")
    @Mapping(target = "serviceTypeId", source = "serviceTypeId")
    public abstract ApplicationListRecordDTO toDto(VwApplicationEntity applicationProjection);

    public abstract List<ApplicationListRecordDTO> toDtoList(List<VwApplicationEntity> list);

    @AfterMapping
    public void afterMappingToDto(@MappingTarget ApplicationListRecordDTO target, VwApplicationEntity source){
        if(source.getOfficialNotesDetails() != null && source.getOfficialNotesDetails().size() == 1){
            target.setOfficialNoteKind(OfficialNoteKind.fromCode(source.getOfficialNotesDetails().get(0).getOfficialNoteKindCode()));
        }
        if(source.getInquiryKinds() != null && source.getInquiryKinds().size() == 1){
            target.setInquiryKind(InquiryKind.fromCode(source.getInquiryKinds().get(0).getInquiryKindCode()));
        }
    }
}
