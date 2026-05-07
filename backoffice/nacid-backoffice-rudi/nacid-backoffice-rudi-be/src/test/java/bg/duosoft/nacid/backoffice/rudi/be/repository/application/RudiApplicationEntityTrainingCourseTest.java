package bg.duosoft.nacid.backoffice.rudi.be.repository.application;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CountryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.LanguageEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.RudiApplicationEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.TrainingCourseEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.TrainingLocationEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * User: ggeorgiev
 * Date: 05.01.2023
 * Time: 18:05
 */
public class RudiApplicationEntityTrainingCourseTest extends RudiApplicationEntityTestBase {
    @Test
//    @Disabled
    public void saveTrainingCourse() {
        RudiApplicationEntity ran = createBaseRudiApplicationEntity();

        TrainingCourseEntity tc = new TrainingCourseEntity();
        ran.setTrainingCourse(tc);
        tc.setDiplomaNumber("alabala");
        tc.setApplication(ran);
        tc.setTrainingLocations(new ArrayList<>());
        tc.setThesisTopic("tt");
        tc.setThesisTopicEn("tten");
        tc.setThesisDefenceDate(LocalDate.of(2022, 1, 1));
        tc.setThesisBibliography(1);
        tc.setThesisVolume(2);
        tc.setThesisAnnotation("ta");
        tc.setThesisAnnotationEn("taen");
        tc.setThesisLanguage(new LanguageEntity("BG"));
        tc.setQualification("qan");
        tc.setOriginalQualification("orgqan");


        TrainingLocationEntity tln = new TrainingLocationEntity();
        tln.setCountry(new CountryEntity("BG", null, null, null, null));
        tln.setCity("Sofia");
        tln.setTrainingCourse(tc);
        tc.getTrainingLocations().add(tln);
        ran = rudiApplicationRepository.saveAndFlush(ran);
        assertNotNull(ran);
        assertNotNull(tc);
        assertNotNull(tc.getTrainingLocations());
        assertEquals(1, tc.getTrainingLocations().size());
        tln = tc.getTrainingLocations().get(0);
        assertEquals("alabala", tc.getDiplomaNumber());
        assertEquals("Sofia", tln.getCity());
        assertEquals("BG", tln.getCountry().getId());


        assertEquals("tt", tc.getThesisTopic());
        assertEquals("tten", tc.getThesisTopicEn());
        assertEquals("ta", tc.getThesisAnnotation());
        assertEquals("taen", tc.getThesisAnnotationEn());
        assertEquals("qan", tc.getQualification());
        assertEquals("orgqan", tc.getOriginalQualification());
        assertNotNull(tc.getThesisLanguage());
        assertEquals("BG", tc.getThesisLanguage().getId());
        assertEquals(1, tc.getThesisBibliography());
        assertEquals(2, tc.getThesisVolume());
        assertEquals(LocalDate.of(2022, 1, 1), tc.getThesisDefenceDate());


    }
}
