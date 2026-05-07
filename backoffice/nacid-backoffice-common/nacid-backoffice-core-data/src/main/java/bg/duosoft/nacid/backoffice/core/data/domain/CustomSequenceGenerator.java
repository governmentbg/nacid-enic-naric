package bg.duosoft.nacid.backoffice.core.data.domain;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DocumentTypeEntity;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * User: ggeorgiev
 * Date: 23.08.2022
 * Time: 15:54
 */
public class CustomSequenceGenerator implements IdentifierGenerator{
    private String sequence;
    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        sequence = params.getProperty("sequence");
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        try {
            if (o instanceof CustomSequenceEntity e) {
                if (e.getId() != null) {
                    return e.getId();
                }
            } else {
                throw new RuntimeException("The entity " + o.getClass() + " should implement " + CustomSequenceEntity.class.getName() + " in order to use CustomSequenceGenerator");
            }
            if (StringUtils.isEmpty(sequence)) {
                throw new RuntimeException("Unknown sequence. The sequence parameter should be defined! ");
            }
            PreparedStatement statement =  sharedSessionContractImplementor.connection().prepareStatement("SELECT nextval(?)");
            statement.setString(1, sequence);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
