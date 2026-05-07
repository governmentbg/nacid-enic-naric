package bg.duosoft.nacidservicesbe.domain.entity.rudi;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.11.2022
 * Time: 17:16
 */
public interface RudiTrainingCourseRelated {

    RudiTrainingCourseIndexIdEntity getId();
    void setId(RudiTrainingCourseIndexIdEntity id);
    void setTrainingCourse(RudiTrainingCourseEntity trainingCourse);
}
