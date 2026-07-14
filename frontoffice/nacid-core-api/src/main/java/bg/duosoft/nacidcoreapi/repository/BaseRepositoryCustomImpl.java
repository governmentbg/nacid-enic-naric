package bg.duosoft.nacidcoreapi.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseRepositoryCustomImpl {

    @PersistenceContext
    protected EntityManager em;

}
