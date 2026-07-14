package bg.duosoft.nacid.backoffice.rudi.be.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseRepositoryCustomImpl {

    @PersistenceContext
    protected EntityManager em;

}
