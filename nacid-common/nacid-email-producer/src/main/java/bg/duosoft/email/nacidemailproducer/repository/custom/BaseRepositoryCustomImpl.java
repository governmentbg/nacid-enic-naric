package bg.duosoft.email.nacidemailproducer.repository.custom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseRepositoryCustomImpl {

    @PersistenceContext(unitName = "pdbEntityManager")
    protected EntityManager em;

}
