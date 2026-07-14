package bg.duosoft.nacidservicesbe.service.utils;

import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationIdIndexIdEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.OfficialNoteDetailsEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.OfficialNoteFullEntity;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.02.2023
 * Time: 16:18
 */
public class OfficialNotesEntityUtils {

    public static void preSaveOfficialNoteDetails(OfficialNoteFullEntity toSave){
        int idx = 0;
        if(toSave.getOfficialNoteDetails() != null){
            for(OfficialNoteDetailsEntity details: toSave.getOfficialNoteDetails()) {
                details.setOfficialNoteApplication(toSave);
                details.setId(new ApplicationIdIndexIdEntity(idx++, toSave.getId()));
            }
        }
    }
}
