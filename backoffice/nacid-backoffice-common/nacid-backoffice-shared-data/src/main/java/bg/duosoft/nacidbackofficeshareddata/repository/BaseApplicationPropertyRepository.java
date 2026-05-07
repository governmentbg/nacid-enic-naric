package bg.duosoft.nacidbackofficeshareddata.repository;


import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationPropertyEntity;

public interface BaseApplicationPropertyRepository {

    ApplicationPropertyEntity selectById(String id);

}
