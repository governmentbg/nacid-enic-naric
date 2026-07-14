package bg.duosoft.nacid.backoffice.core.be.service;

import bg.duosoft.nacid.backoffice.core.be.TestBase;
import bg.duosoft.nacid.backoffice.core.be.service.common.PersonService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * User: ggeorgiev
 * Date: 06.01.2023
 * Time: 14:00
 */
public class PersonServiceTest extends TestBase {
    @Autowired
    private PersonService personService;
    @Test
    public void testFindPerson() {
        List<PersonDTO> persons = personService.selectByCivilId("EGN", "1010101010", null, null, null);
        assertEquals(1, persons.size());
    }
}
