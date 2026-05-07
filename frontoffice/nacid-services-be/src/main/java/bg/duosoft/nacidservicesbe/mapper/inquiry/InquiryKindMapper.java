package bg.duosoft.nacidservicesbe.mapper.inquiry;

import bg.duosoft.nacidfrontofficedto.services.inquiry.InquiryKind;
import bg.duosoft.nacidservicesbe.domain.entity.lib.InquiryKindEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.02.2023
 * Time: 15:18
 */
@Mapper(componentModel = "spring")
public abstract class InquiryKindMapper extends BaseObjectMapper<InquiryKindEntity, InquiryKind> {

    @Override
    public InquiryKindEntity toEntity(InquiryKind inquiryKind) {
        if(inquiryKind != null){
            InquiryKindEntity kind = new InquiryKindEntity();
            kind.setInquiryKindCode(inquiryKind.getCode());
            return kind;
        }
        return null;
    }

    @Override
    public InquiryKind toDto(InquiryKindEntity inquiryDetails) {
        if(inquiryDetails != null && inquiryDetails.getInquiryKindCode() != null){
            return InquiryKind.fromCode(inquiryDetails.getInquiryKindCode());
        }
        return null;
    }
}
