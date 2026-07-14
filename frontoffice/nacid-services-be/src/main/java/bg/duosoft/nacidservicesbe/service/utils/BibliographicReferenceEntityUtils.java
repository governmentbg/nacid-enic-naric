package bg.duosoft.nacidservicesbe.service.utils;

import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationIdIndexIdEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.BibliographicReferenceFullEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.BibliographicReferenceLanguageEntity;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.03.2023
 * Time: 15:42
 */
public class BibliographicReferenceEntityUtils {

    public static void preSaveLanguages(BibliographicReferenceFullEntity toSave){
        int idx = 0;
        if(toSave.getLanguages() != null){
            for(BibliographicReferenceLanguageEntity lang: toSave.getLanguages()) {
                lang.setBibliographicReferenceApplication(toSave);
                lang.setId(new ApplicationIdIndexIdEntity(idx++, toSave.getId()));
            }
        }
    }
}
