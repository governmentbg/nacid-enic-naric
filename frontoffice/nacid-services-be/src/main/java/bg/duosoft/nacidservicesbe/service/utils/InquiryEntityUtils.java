package bg.duosoft.nacidservicesbe.service.utils;

import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationIdIndexIdEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.InquiryKindEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.InquiryFullEntity;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.02.2023
 * Time: 16:18
 */
public class InquiryEntityUtils {

    public static void preSaveInquiryDetails(InquiryFullEntity toSave){
        int idx = 0;
        if(toSave.getInquiryKinds() != null){
            for(InquiryKindEntity kindEntity: toSave.getInquiryKinds()) {
                kindEntity.setInquiryApplication(toSave);
                kindEntity.setId(new ApplicationIdIndexIdEntity(idx++, toSave.getId()));
            }
        }
    }
}
