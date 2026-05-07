package bg.duosoft.nacidservicesbe.service.utils;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidservicesbe.domain.entity.regprof.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.12.2022
 * Time: 14:14
 */
public class RegprofTrainingExperienceEntityUtils {

    public static void keepTrainingExperienceDBDetails(RegprofTrainingExperienceEntity dbTrainingExperience, RegprofTrainingExperienceEntity toSave, Integer regprofApplicationId){
        if(dbTrainingExperience != null) {
            toSave.setId(dbTrainingExperience.getId());
        }
        toSave.setRegprofApplicationId(regprofApplicationId);
    }

    public static void preSaveTrainingExperience(RegprofTrainingExperienceEntity toSave){
        preSaveTrainingCourse(toSave.getTrainingCourse(), toSave);
        preSaveExperience(toSave.getExperience(), toSave);
    }

    public static void preSaveTrainingCourse(RegprofTrainingCourseEntity trainingCourse, RegprofTrainingExperienceEntity trainingExperience){
        if(trainingCourse != null){
            trainingCourse.setTrainingExperience(trainingExperience);
            trainingCourse.setTrainingCourseId(trainingExperience.getId());
            if(trainingCourse.getAllSpecialities()!= null){
                int i = 0;
                for(RegprofTrainingCourseSpecialitiesEntity spec: trainingCourse.getAllSpecialities()){
                    spec.setTrainingCourse(trainingCourse);
                    spec.setId(new RegprofTrainingCourseSpecialitiesIdEntity(i++, trainingCourse.getTrainingCourseId()));
                }
            }
            if(trainingCourse.getSecondaryTrainingCourse() != null){
                trainingCourse.getSecondaryTrainingCourse().setTrainingCourse(trainingCourse);
                trainingCourse.getSecondaryTrainingCourse().setTrainingCourseId(trainingCourse.getTrainingCourseId());
            }
            if(trainingCourse.getHigherTrainingCourse() != null){
                trainingCourse.getHigherTrainingCourse().setTrainingCourse(trainingCourse);
                trainingCourse.getHigherTrainingCourse().setTrainingCourseId(trainingCourse.getTrainingCourseId());
            }
            if(trainingCourse.getPostgraduateTrainingCourse() != null){
                trainingCourse.getPostgraduateTrainingCourse().setTrainingCourse(trainingCourse);
                trainingCourse.getPostgraduateTrainingCourse().setTrainingCourseId(trainingCourse.getTrainingCourseId());
            }
        }
    }

    public static void preSaveExperience(RegprofExperienceEntity experience, RegprofTrainingExperienceEntity trainingExperience){
        if(experience != null){
            experience.setTrainingExperience(trainingExperience);
            experience.setTrainingExperienceId(trainingExperience.getId());
            if(experience.getDocuments() != null){
                int i = 0;
                for(RegprofExperienceDocumentEntity expDoc: experience.getDocuments()){
                    expDoc.setExperience(experience);
                    expDoc.setId(new RegprofExperienceDocumentIdEntity(i++, experience.getTrainingExperienceId()));
                    if(expDoc.getDocumentDates() != null){
                        int y = 0;
                        for(RegprofExperienceDocumentDateEntity docDate: expDoc.getDocumentDates()){
                            docDate.setExperienceDocument(expDoc);
                            docDate.setId(new RegprofExperienceDocumentDateIdEntity(y++, expDoc.getId()));
                        }
                    }
                }
            }
        }
    }
}
