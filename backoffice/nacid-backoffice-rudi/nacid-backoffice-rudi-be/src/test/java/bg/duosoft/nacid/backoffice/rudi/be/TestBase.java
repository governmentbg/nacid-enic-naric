package bg.duosoft.nacid.backoffice.rudi.be;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: ggeorgiev
 * Date: 26.08.2022
 * Time: 11:43
 */
@SpringBootTest
@Transactional
@Rollback
public abstract class TestBase {
}
