package bg.duosoft.nacidcoredata.mapper;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.01.2023
 * Time: 12:55
 */
@Mapper(componentModel = "spring")
public abstract class ApplicationSubtypeMapper extends BaseObjectMapper<String, ApplicationSubtype> {

    @Override
    public ApplicationSubtype toDto(String s) {
        return ApplicationSubtype.fromCode(s);
    }

    @Override
    public String toEntity(ApplicationSubtype applicationSubtype) {
        if(applicationSubtype != null){
            return applicationSubtype.getCode();
        }
        return null;
    }
}
