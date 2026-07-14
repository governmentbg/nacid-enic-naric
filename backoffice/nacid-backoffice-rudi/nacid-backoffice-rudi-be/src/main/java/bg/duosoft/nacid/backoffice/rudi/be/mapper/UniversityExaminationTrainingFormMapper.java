package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.UniversityExaminationTrainingFormDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.UniversityExaminationTrainingFormEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = { ReferenceDataMapper.class})
public abstract class UniversityExaminationTrainingFormMapper extends BaseObjectMapper<UniversityExaminationTrainingFormEntity, UniversityExaminationTrainingFormDTO> {
}
