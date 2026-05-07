package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.udirec;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationRecognizedSpecialityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.udirec.status.UdirecStatusDataDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.StatusDataMapperBase;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.StatusDataMapperConfig;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.StatusDataMapperUtils;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", config = StatusDataMapperConfig.class, uses = {
        IntegerToBooleanMapper.class,
})
public abstract class UdirecStatusDataMapper extends StatusDataMapperBase<UdirecStatusDataDTO> {

    @InheritConfiguration(name = "toStatusDataSectionBase")
    @Mapping(target = "recognizedQualification", source = "applicationRecognizedDetails.recognizedQualification")
    @Mapping(target = "recognizedEduLevel.id", source = "applicationRecognizedDetails.recognizedEduLevel")
    public abstract UdirecStatusDataDTO toStatusDataSection(RudiApplicationDTO application);

    @InheritInverseConfiguration(name = "toStatusDataSection")
    public abstract void overrideApplicationData(UdirecStatusDataDTO source, @MappingTarget RudiApplicationDTO target);

    @AfterMapping
    public void afterOverride(UdirecStatusDataDTO source, @MappingTarget RudiApplicationDTO target) {
        super.afterOverride(source, target);

        StatusDataMapperUtils.afterOverrideUdirecDocrecStatusData(source, target);

        List<ApplicationRecognizedSpecialityDTO> recognizedSpecialities = new ArrayList<>();
        if (CollectionUtils.isEmpty(target.getRecognizedSpecialities())) {
            target.setRecognizedSpecialities(new ArrayList<>());
        }

        if (!CollectionUtils.isEmpty(source.getRecognizedSpecialities())) {
                source.getRecognizedSpecialities().stream().forEach(speciality -> {
                    ApplicationRecognizedSpecialityDTO specialityDTO = target.getRecognizedSpecialities().stream().filter(r -> r.getSpeciality().equals(speciality)).findFirst().orElse(null);
                    if (Objects.isNull(specialityDTO)) {
                        specialityDTO = new ApplicationRecognizedSpecialityDTO(null, target.getApplication().getId(), speciality);
                    }
                    recognizedSpecialities.add(specialityDTO);
                });
        }
        target.setRecognizedSpecialities(recognizedSpecialities);
    }

    @AfterMapping
    public void afterToStatusDataSection(RudiApplicationDTO source, @MappingTarget UdirecStatusDataDTO target) {
        super.afterToStatusDataSection(source, target);
        StatusDataMapperUtils.afterToUdirecDocrecStatusDataSection(source, target);

        List<String> recognizedSpecialities = !CollectionUtils.isEmpty(source.getRecognizedSpecialities()) ? source.getRecognizedSpecialities().stream().map(ApplicationRecognizedSpecialityDTO::getSpeciality).toList() : new ArrayList<>();
        target.setRecognizedSpecialities(recognizedSpecialities);
    }
}
