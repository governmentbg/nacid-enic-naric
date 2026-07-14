package bg.duosoft.nacidservicesbe.cloner.entity.inquiry;

import bg.duosoft.nacidservicesbe.cloner.entity.base.BaseCloner;
import bg.duosoft.nacidservicesbe.cloner.entity.common.ApplicationEntityCloner;
import bg.duosoft.nacidservicesbe.domain.entity.lib.InquiryFullEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 10.04.2023
 * Time: 14:18
 */
@Mapper(componentModel = "spring", uses = {
        ApplicationEntityCloner.class
})
public abstract class InquiryEntityCloner extends BaseCloner<InquiryFullEntity> {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "inquiryKinds", ignore = true)
    public abstract InquiryFullEntity clone(InquiryFullEntity source);
}
