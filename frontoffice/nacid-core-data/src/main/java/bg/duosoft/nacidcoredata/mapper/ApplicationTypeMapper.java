package bg.duosoft.nacidcoredata.mapper;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.01.2023
 * Time: 12:53
 */
@Mapper(componentModel = "spring")
public abstract class ApplicationTypeMapper extends BaseObjectMapper<String, ApplicationType> {

    @Override
    public ApplicationType toDto(String s) {

        return ApplicationType.fromCode(s);
    }

    @Override
    public String toEntity(ApplicationType applicationType) {
        if(applicationType != null){
            return applicationType.getCode();
        }
        return null;
    }
}
