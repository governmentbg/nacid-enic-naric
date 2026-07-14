package bg.duosoft.nacidservicesbe.service.utils;

import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationAttachedDocEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationIdIndexIdEntity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 18.11.2022
 * Time: 13:52
 */
public class AttachmentsEntityUtils {

    public static void preSaveAttachedDocs(List<ApplicationAttachedDocEntity> attachedDocs, Integer  applicationId){
        if(attachedDocs != null){
            int index = 0;

            for(ApplicationAttachedDocEntity att: attachedDocs){
                ApplicationIdIndexIdEntity id = new ApplicationIdIndexIdEntity();
                id.setApplicationId(applicationId);
                id.setIndex(index++);

                att.setId(id);
                if(att.getAttachment() != null){
                    att.getAttachment().setId(id);
                    att.getAttachment().setAttachedDoc(att);
                }
            }
        }
    }
}
