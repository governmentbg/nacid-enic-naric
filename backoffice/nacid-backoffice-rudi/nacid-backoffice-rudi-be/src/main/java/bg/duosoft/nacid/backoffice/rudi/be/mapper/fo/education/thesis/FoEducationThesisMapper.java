package bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.education.thesis;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.nomenclature.FoLanguageMapper;
import bg.duosoft.nacidfrontofficedto.services.docdegrees.DocEducationDetailsDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring", uses = {FoLanguageMapper.class})
public abstract class FoEducationThesisMapper {

    @Mapping(target = "thesisTopic", source = "dissertationTheme")
    @Mapping(target = "thesisTopicEn", source = "dissertationThemeEn")
    @Mapping(target = "thesisDefenceDate", source = "dissertationDate")
    @Mapping(target = "thesisLanguage", source = "dissertationLanguage")
    @Mapping(target = "thesisBibliography", source = "dissertationBiblioTitlesCount")
    @Mapping(target = "thesisVolume", source = "dissertationPagesCount")
    @Mapping(target = "thesisAnnotation", source = "dissertationAnnotation")
    @Mapping(target = "thesisAnnotationEn", source = "dissertationAnnotationEn")
    @Mapping(target = "scientificSupervisor", source = "scientificSupervisor")
    @Mapping(target = "scientificSupervisorEn", source = "scientificSupervisorEn")
    @Mapping(target = "reviewers", source = "reviewers")
    @Mapping(target = "reviewersEn", source = "reviewersEn")
    @Mapping(target = "juryChair", source = "juryChair")
    @Mapping(target = "juryChairEn", source = "juryChairEn")
    @Mapping(target = "juryMembers", source = "juryMembers")
    @Mapping(target = "juryMembersEn", source = "juryMembersEn")
    @BeanMapping(ignoreByDefault = true)
    public abstract void overrideThesisData(DocEducationDetailsDTO source, @MappingTarget TrainingCourseDTO target);

}
