package bg.duosoft.nacidservicesbe.service.utils;

import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationIdIndexIdEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.PublicAccessFullEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.PublicAccessInfoFormEntity;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.08.2023
 * Time: 17:12
 */
public class PublicAccessEntityUtils {

    public static void preSavePublicAccessDetails(PublicAccessFullEntity toSave){
        int idx = 0;
        if(toSave.getDetails() != null){
            for(PublicAccessInfoFormEntity details: toSave.getDetails()) {
                details.setPublicAccessApplication(toSave);
                details.setId(new ApplicationIdIndexIdEntity(idx++, toSave.getId()));
            }
        }
    }
}
