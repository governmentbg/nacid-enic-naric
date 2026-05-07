package bg.duosoft.nacidservicesbe.service.utils;

import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiTrainingCourseEntity;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiTrainingCourseIndexIdEntity;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiTrainingCourseRelated;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.11.2022
 * Time: 15:51
 */
public class TrainingCourseEntityUtils {

    public static void keepTrainingCourseDBDetails(RudiTrainingCourseEntity dbTrainingCourse, RudiTrainingCourseEntity toSave, Integer rudiApplicationId){
        if(dbTrainingCourse != null) {
            toSave.setId(dbTrainingCourse.getId());

            if(dbTrainingCourse.getDiplomaOwner() != null && toSave.getDiplomaOwner() != null){
                toSave.getDiplomaOwner().setId(dbTrainingCourse.getDiplomaOwner().getId());
            }
            if(dbTrainingCourse.getRecognitionPurposes() != null && dbTrainingCourse.getRecognitionPurposes().size() == 0 && toSave.getRecognitionPurposes() == null){
                toSave.setRecognitionPurposes(new ArrayList<>());
            }
            if(dbTrainingCourse.getTrainingSpecialities() != null && dbTrainingCourse.getTrainingSpecialities().size() == 0 && toSave.getTrainingSpecialities() == null){
                toSave.setTrainingSpecialities(new ArrayList<>());
            }
        }
        toSave.setRudiApplicationId(rudiApplicationId);
    }

    public static void preSaveTrainingCourse(RudiTrainingCourseEntity toSave){
        preSaveTrainingCourseRelatedList(toSave.getTrainingForms(), toSave);
        preSaveTrainingCourseRelatedList(toSave.getGraduationWays(), toSave);
        preSaveTrainingCourseRelatedList(toSave.getTrainingLocations(), toSave);
        preSaveTrainingCourseRelatedList(toSave.getTrainingSpecialities(), toSave);
        preSaveTrainingCourseRelatedList(toSave.getTrainingUniversities(), toSave);
        preSaveTrainingCourseRelatedList(toSave.getRecognitionPurposes(), toSave);
    }

    public static void preSaveTrainingCourseRelatedList(List<? extends RudiTrainingCourseRelated> list, RudiTrainingCourseEntity trainingCourse){
        if(list != null) {
            int index = 0;
            for (RudiTrainingCourseRelated rel : list) {
                if(rel.getId() == null){
                    rel.setId(new RudiTrainingCourseIndexIdEntity());
                }
                rel.getId().setIndex(index++);
                rel.getId().setTrainingCourseId(trainingCourse.getId());
                rel.setTrainingCourse(trainingCourse);
            }
        }
    }
}
