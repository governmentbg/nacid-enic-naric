package bg.duosoft.nacidfrontofficedto.nomenclature.base;

import lombok.Data;

/**
 * User: ggeorgiev
 * Date: 20.04.2022
 * Time: 15:03
 */
@Data
public abstract class NomenclatureBaseImpl<T> implements NomenclatureBase<T> {
    protected T id;
    protected String name;
    protected Boolean isActive;
}
