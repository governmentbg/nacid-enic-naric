package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base;

/**
 * User: ggeorgiev
 * Date: 20.04.2022
 * Time: 13:08
 */
public interface NomenclatureBase<T> {
    T getId();
    String getName();
    Boolean getIsActive();

    void setId(T id);
    void setName(String name);

    void setIsActive(Boolean isActive);
}
