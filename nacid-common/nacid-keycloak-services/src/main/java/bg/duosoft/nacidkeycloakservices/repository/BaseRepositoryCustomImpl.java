package bg.duosoft.nacidkeycloakservices.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseRepositoryCustomImpl {

    @PersistenceContext(unitName = "keycloakEntityManager")
    protected EntityManager em;

}
