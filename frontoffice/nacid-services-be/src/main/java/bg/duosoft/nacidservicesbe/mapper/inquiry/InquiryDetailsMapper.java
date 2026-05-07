package bg.duosoft.nacidservicesbe.mapper.inquiry;

import bg.duosoft.nacidfrontofficedto.services.inquiry.InquiryDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.lib.InquiryFullEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 17.03.2023
 * Time: 14:46
 */
@Mapper(componentModel = "spring", uses = {
        InquiryKindMapper.class
})
public abstract class InquiryDetailsMapper extends BaseObjectMapper<InquiryFullEntity, InquiryDetailsDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "inquiryKinds", source = "inquiryKinds")
    @Mapping(target = "inquiryAim", source = "inquiryAim")
    @Mapping(target = "periodFrom", source = "periodFrom")
    @Mapping(target = "periodTo", source = "periodTo")
    @Mapping(target = "previousInquiryNum", source = "previousInquiryNum")
    public abstract InquiryFullEntity toEntity(InquiryDetailsDTO inquiryDetailsDTO);

    @InheritInverseConfiguration(name = "toEntity")
    public abstract InquiryDetailsDTO toDto(InquiryFullEntity inquiryFullEntity);

    public static void copyDetailsToApplication(InquiryFullEntity target, InquiryFullEntity source){
        target.getInquiryKinds().clear();
        if(source.getInquiryKinds() != null) {
            target.getInquiryKinds().addAll(source.getInquiryKinds());
        }
        target.setInquiryAim(source.getInquiryAim());
        target.setPeriodFrom(source.getPeriodFrom());
        target.setPeriodTo(source.getPeriodTo());
        target.setPreviousInquiryNum(source.getPreviousInquiryNum());
    }
}
