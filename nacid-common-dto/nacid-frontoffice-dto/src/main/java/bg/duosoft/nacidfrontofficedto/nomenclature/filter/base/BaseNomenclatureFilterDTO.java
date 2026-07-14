package bg.duosoft.nacidfrontofficedto.nomenclature.filter.base;

import bg.duosoft.nacidfrontofficedto.Pageable;
import bg.duosoft.nacidfrontofficedto.Sortable;
import bg.duosoft.nacidfrontofficedto.utils.constants.NomenclatureSortFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseNomenclatureFilterDTO<T> implements Sortable, Pageable {
    protected String order = this.DESC_ORDER;
    protected String orderBy = NomenclatureSortFields.NAME;
    protected Integer page = this.DEFAULT_PAGE;
    protected Integer pageSize = this.DEFAULT_PAGE_SIZE;
    protected T id;
    protected String name;
    protected Boolean isActive;
}