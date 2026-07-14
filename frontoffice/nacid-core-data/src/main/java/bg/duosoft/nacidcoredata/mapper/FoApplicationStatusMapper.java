package bg.duosoft.nacidcoredata.mapper;

import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatus;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.01.2023
 * Time: 12:56
 */
@Mapper(componentModel = "spring")
public abstract class FoApplicationStatusMapper extends BaseObjectMapper<String, FoApplicationStatus> {

    @Override
    public FoApplicationStatus toDto(String s) {
        return FoApplicationStatus.fromCode(s);
    }

    @Override
    public String toEntity(FoApplicationStatus foApplicationStatus) {
        if(foApplicationStatus != null){
            return foApplicationStatus.getCode();
        }
        return null;
    }
}
