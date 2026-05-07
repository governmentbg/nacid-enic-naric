package bg.duosoft.nacidcoredata.domain.entity.nomenclature.base;

import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 20.04.2022
 * Time: 13:12
 */
public interface NomenclatureEntityBase<T> extends Serializable {
    public T getId();
    public String getName();
    public Integer getActive();

    public void setId(T id);
    public void setName(String name);
    public void setActive(Integer active);
}
