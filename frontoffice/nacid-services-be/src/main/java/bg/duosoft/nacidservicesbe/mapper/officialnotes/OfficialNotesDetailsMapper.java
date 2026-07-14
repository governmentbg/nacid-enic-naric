package bg.duosoft.nacidservicesbe.mapper.officialnotes;

import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNoteKind;
import bg.duosoft.nacidservicesbe.domain.entity.lib.OfficialNoteDetailsEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.02.2023
 * Time: 15:18
 */
@Mapper(componentModel = "spring")
public abstract class OfficialNotesDetailsMapper extends BaseObjectMapper<OfficialNoteDetailsEntity, OfficialNoteKind> {

    @Override
    public OfficialNoteDetailsEntity toEntity(OfficialNoteKind officialNoteKind) {
        if(officialNoteKind != null){
            OfficialNoteDetailsEntity details = new OfficialNoteDetailsEntity();
            details.setOfficialNoteKindCode(officialNoteKind.getCode());
            return details;
        }
        return null;
    }

    @Override
    public OfficialNoteKind toDto(OfficialNoteDetailsEntity officialNoteDetailsEntity) {
        if(officialNoteDetailsEntity != null && officialNoteDetailsEntity.getOfficialNoteKindCode() != null){
            return OfficialNoteKind.fromCode(officialNoteDetailsEntity.getOfficialNoteKindCode());
        }
        return null;
    }
}
