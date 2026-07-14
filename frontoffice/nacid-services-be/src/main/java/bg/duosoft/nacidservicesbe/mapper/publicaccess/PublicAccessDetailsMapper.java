package bg.duosoft.nacidservicesbe.mapper.publicaccess;

import bg.duosoft.nacidfrontofficedto.services.publicaccess.PublicAccessDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.lib.PublicAccessFullEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.08.2023
 * Time: 15:34
 */
@Mapper(componentModel = "spring", uses = {
        PublicAccessInfoFormMapper.class
})
public abstract class PublicAccessDetailsMapper extends BaseObjectMapper<PublicAccessFullEntity, PublicAccessDetailsDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "about", source = "about")
    @Mapping(target = "comment", source = "comment")
    @Mapping(target = "details", source = "infoForms")
    public abstract PublicAccessFullEntity toEntity(PublicAccessDetailsDTO publicAccessDetailsDTO);

    @InheritInverseConfiguration(name = "toEntity")
    public abstract PublicAccessDetailsDTO toDto(PublicAccessFullEntity publicAccessFullEntity);

    public static void copyDetailsToApplication(PublicAccessFullEntity target, PublicAccessFullEntity source){
        target.setAbout(source.getAbout());
        target.setComment(source.getComment());
        if(target.getDetails() != null){
            target.getDetails().clear();
        } else {
            target.setDetails(new ArrayList<>());
        }
        target.getDetails().addAll(source.getDetails());
    }

}
