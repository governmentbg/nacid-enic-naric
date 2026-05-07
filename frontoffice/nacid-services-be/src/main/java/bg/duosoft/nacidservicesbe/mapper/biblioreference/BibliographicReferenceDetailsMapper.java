package bg.duosoft.nacidservicesbe.mapper.biblioreference;

import bg.duosoft.nacidfrontofficedto.services.biblioreference.BibliographicReferenceDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.lib.BibliographicReferenceFullEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.03.2023
 * Time: 15:28
 */
@Mapper(componentModel = "spring", uses = {
        BibliographicReferenceLanguageMapper.class,
        IntegerToBooleanMapper.class
})
public abstract class BibliographicReferenceDetailsMapper extends BaseObjectMapper<BibliographicReferenceFullEntity, BibliographicReferenceDetailsDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "languages", source = "searchLanguages")
    @Mapping(target = "searchForeignFlag", source = "foreignSearch")
    @Mapping(target = "searchBgFlag", source = "nacidSearch")
    @Mapping(target = "resultKindCodeForeign", source = "foreignSearchKind.code")
    @Mapping(target = "resultKindCodeBg", source = "nacidSearchKind.code")
    @Mapping(target = "subject", source = "theme")
    @Mapping(target = "keywords", source = "keywords")
    @Mapping(target = "periodFrom", source = "searchFrom")
    @Mapping(target = "periodTo", source = "searchTo")
    public abstract BibliographicReferenceFullEntity toEntity(BibliographicReferenceDetailsDTO bibliographicReferenceDetailsDTO);

    @InheritInverseConfiguration
    @Mapping(target = "foreignSearchKind", expression = "java(bg.duosoft.nacidfrontofficedto.services.biblioreference.BibliographicReferenceResultKind.fromCode(bibliographicReferenceFullEntity.getResultKindCodeForeign()))")
    @Mapping(target = "nacidSearchKind", expression = "java(bg.duosoft.nacidfrontofficedto.services.biblioreference.BibliographicReferenceResultKind.fromCode(bibliographicReferenceFullEntity.getResultKindCodeBg()))")
    public abstract BibliographicReferenceDetailsDTO toDto(BibliographicReferenceFullEntity bibliographicReferenceFullEntity);

    public static void copyDetailsToApplication(BibliographicReferenceFullEntity target, BibliographicReferenceFullEntity source){
        target.getLanguages().clear();
        if(source.getLanguages() != null) {
            target.getLanguages().addAll(source.getLanguages());
        }
        target.setSearchForeignFlag(source.getSearchForeignFlag());
        target.setSearchBgFlag(source.getSearchBgFlag());
        target.setResultKindCodeForeign(source.getResultKindCodeForeign());
        target.setResultKindCodeBg(source.getResultKindCodeBg());
        target.setSubject(source.getSubject());
        target.setKeywords(source.getKeywords());
        target.setPeriodFrom(source.getPeriodFrom());
        target.setPeriodTo(source.getPeriodTo());
    }
}
