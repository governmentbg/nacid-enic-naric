package bg.duosoft.nacidbackofficeshareddata.repository.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationPropertyEntity;
import bg.duosoft.nacidbackofficeshareddata.repository.BaseApplicationPropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


@Slf4j
@Repository
public class BaseApplicationPropertyRepositoryImpl extends BaseRepositoryCustomImpl implements BaseApplicationPropertyRepository {

    @Override
    public ApplicationPropertyEntity selectById(String id) {
        String queryString = "SELECT r FROM ApplicationPropertyEntity r WHERE r.id = :id";
        TypedQuery<ApplicationPropertyEntity> query = em.createQuery(queryString, ApplicationPropertyEntity.class);
        query.setParameter("id", id);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
