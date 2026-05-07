package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
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
public abstract class BaseNomenclatureFilterDTO<T> extends BaseFilterDTO {
    private T id;
    private String name;
    private Boolean isActive;
}