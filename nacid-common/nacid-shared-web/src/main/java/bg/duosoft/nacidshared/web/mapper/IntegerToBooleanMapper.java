package bg.duosoft.nacidshared.web.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class IntegerToBooleanMapper {
    public Integer booleanToInt(Boolean b) {
        return b == null ? null : (b ? 1 : 0);
    }

    public Boolean intToBoolean(Integer integer) {
        return integer == null ? null : integer > 0;
    }
}
