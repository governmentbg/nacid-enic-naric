package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.FacultyEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.UniversityEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.UniversityDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.AddressMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CountryMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, CountryMapper.class, AddressMapper.class, FacultyMapper.class})
public abstract class UniversityMapper extends BaseObjectMapper<UniversityEntity, UniversityDTO> {

    @Mapping(target = "isActive", source = "active")
    public abstract UniversityDTO toDto(UniversityEntity e);

    @AfterMapping
    protected void afterToEntity(UniversityDTO source, @MappingTarget UniversityEntity target) {
        List<FacultyEntity> faculties = target.getFaculties();
        if (!CollectionUtils.isEmpty(faculties)) {
            for (FacultyEntity faculty : faculties) {
                faculty.setUniversity(target);
            }
        }
    }
}
