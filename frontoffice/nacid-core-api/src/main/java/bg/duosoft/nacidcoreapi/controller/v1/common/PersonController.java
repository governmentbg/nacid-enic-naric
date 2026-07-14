package bg.duosoft.nacidcoreapi.controller.v1.common;

import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.util.civil_id.CivilIdUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 09.03.2023
 * Time: 15:53
 */
@Slf4j
@RestController
@Api(tags = Tags.PERSON)
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
public class PersonController {

    @GetMapping("/birth-date/extraction/{civilId}")
    public LocalDate extractBirthDateFromCivilId(@PathVariable String civilId) {
        try {
            return CivilIdUtils.getBirthDate(civilId);
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }
}
