package bg.duosoft.nacidservicesbe.service.utils;

import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationIdIndexIdEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.DocumentDeliveryDetailsEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.DocumentDeliveryFullEntity;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.03.2023
 * Time: 13:37
 */
public class DocumentDeliveryEntityUtils {

    public static void preSaveDocumentDeliveryDetails(DocumentDeliveryFullEntity toSave){
        int idx = 0;
        if(toSave.getDeliveryDetails() != null){
            for(DocumentDeliveryDetailsEntity details: toSave.getDeliveryDetails()) {
                details.setDocumentDeliveryApplication(toSave);
                details.setId(new ApplicationIdIndexIdEntity(idx++, toSave.getId()));
            }
        }
    }
}
