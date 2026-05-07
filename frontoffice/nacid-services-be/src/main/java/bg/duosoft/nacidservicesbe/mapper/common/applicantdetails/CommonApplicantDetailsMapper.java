package bg.duosoft.nacidservicesbe.mapper.common.applicantdetails;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationDocumentReceiveMethodDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationDocumentReceiveMethodEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidservicesbe.mapper.common.application.ApplicationDocumentReceiveMethodMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.11.2022
 * Time: 13:57
 */
@Mapper(componentModel = "spring", config = BaseApplicantDetailsMapper.class, uses = {})
public abstract class CommonApplicantDetailsMapper extends BaseObjectMapper<ApplicationEntity, CommonApplicantDetailsDTO> {

    @Autowired
    private ApplicationDocumentReceiveMethodMapper applicationDocumentReceiveMethodMapper;

    @InheritConfiguration(name = "baseApplicantDetailsMapping")
    @Mapping(target = "applicationDocumentReceiveMethods", source = "resultReceive")
    public abstract ApplicationEntity toEntity(CommonApplicantDetailsDTO applicantDetailsDTO);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "applicantHasRepresentative", expression = "java(applicationEntity.getRepresentative() != null)")
    @Mapping(target = "hasContactAddress", expression = "java(applicationEntity.getContactAddress() != null)")
    public abstract CommonApplicantDetailsDTO toDto(ApplicationEntity applicationEntity);


    public List<ApplicationDocumentReceiveMethodEntity> toDocReceiveEntityList(ApplicationDocumentReceiveMethodDTO dto){
        List<ApplicationDocumentReceiveMethodEntity> list = new ArrayList<>();
        if(dto != null){
            ApplicationDocumentReceiveMethodEntity entity = applicationDocumentReceiveMethodMapper.toEntity(dto);
            if(entity != null){
                list.add(entity);
            }
        }
        return list;
    }

    public ApplicationDocumentReceiveMethodDTO toDocReceiveDto(List<ApplicationDocumentReceiveMethodEntity> list){
        if(list != null && list.size() >0 && list.get(0) != null){
            return applicationDocumentReceiveMethodMapper.toDto(list.get(0));
        }
        return null;
    }
}
