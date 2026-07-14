package bg.duosoft.nacidservicesbe.mapper.signal;

import bg.duosoft.nacidfrontofficedto.services.signal.SignalDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.lib.SignalFullEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.03.2023
 * Time: 17:14
 */
@Mapper(componentModel = "spring")
public abstract class SignalDetailsMapper extends BaseObjectMapper<SignalFullEntity, SignalDetailsDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "violationText", source = "violationDescription")
    @Mapping(target = "violationPlace", source = "violationPlace")
    @Mapping(target = "checktimeText", source = "checkRequirement")
    @Mapping(target = "damageText", source = "damagesDescription")
    @Mapping(target = "actionsText", source = "measuresTaken")
    public abstract SignalFullEntity toEntity(SignalDetailsDTO signalDetailsDTO);

    @InheritInverseConfiguration(name = "toEntity")
    public abstract SignalDetailsDTO toDto(SignalFullEntity signalFullEntity);

    public static void copyDetailsToApplication(SignalFullEntity target, SignalFullEntity source){
        target.setActionsText(source.getActionsText());
        target.setChecktimeText(source.getChecktimeText());
        target.setDamageText(source.getDamageText());
        target.setViolationPlace(source.getViolationPlace());
        target.setViolationText(source.getViolationText());
    }
}
