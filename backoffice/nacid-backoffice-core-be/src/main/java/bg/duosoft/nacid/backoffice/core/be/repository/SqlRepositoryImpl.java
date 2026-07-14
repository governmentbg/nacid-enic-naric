package bg.duosoft.nacid.backoffice.core.be.repository;

import bg.duosoft.nacidbackofficeshareddata.repository.impl.BaseRepositoryCustomImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * User: Georgi
 * Date: 17.7.2020 г.
 * Time: 14:27
 */
@Repository
public class SqlRepositoryImpl extends BaseRepositoryCustomImpl implements SqlRepository {
    public int execute(String sql, Map<String, Object> args) {
        Query q = em.createNativeQuery(sql);
        _setArgs(args, q);
        return q.executeUpdate();
    }
    public List<Object[]> selectRowsAsObjectArray(String sql, Map<String, Object> args) {
        Query q = em.createNativeQuery(sql);
        _setArgs(args, q);
        return q.getResultList();
    }

    @Override
    public List<Map<String, Object>> selectRowsAsMap(String sql, Map<String, Object> args) {
        Query q = em.createNativeQuery(sql);
        _setArgs(args, q);
        return q.unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
        .getResultList();
    }
    public List<String> getSqlParameterNames(String sql) {
        Query q = em.createNativeQuery(sql);
        return q.getParameters().stream().map(p -> p.getName()).collect(Collectors.toList());
    }
    private void _setArgs(Map<String, Object> args, Query q) {
        if (args != null) {
            //setting only the parameters, contained in the SQL!
            q.getParameters().stream().map(r -> r.getName()).forEach(p -> q.setParameter(p, args.get(p)));
        }
    }
}
