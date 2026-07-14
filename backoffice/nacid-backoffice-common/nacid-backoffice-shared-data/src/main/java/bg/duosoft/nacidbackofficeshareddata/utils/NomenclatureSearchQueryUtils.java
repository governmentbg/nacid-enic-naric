package bg.duosoft.nacidbackofficeshareddata.utils;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Sortable;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.BaseNomenclatureFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.sort.NomenclatureSortFields;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;

public class NomenclatureSearchQueryUtils {

    public static <F extends BaseFilterDTO> void orderQuery(F filter, boolean isCount, StringBuilder queryBuilder) {
        if (!isCount) {
            String sortColumn = filter.getOrderBy();
            String sortOrder = filter.getOrder();

            if (StringUtils.hasText(sortColumn)) {
                if (!(Sortable.ASC_ORDER.equalsIgnoreCase(sortOrder) || Sortable.DESC_ORDER.equalsIgnoreCase(sortOrder))) {
                    sortOrder = Sortable.ASC_ORDER;
                }

                String sortFields = NomenclatureSortFields.sorterColumnMap().get(sortColumn);
                if (StringUtils.hasText(sortFields)) {
                    String[] columns = sortFields.split(",");
                    String order = String.join(" " + sortOrder + " , ", columns) + " " + sortOrder;
                    queryBuilder.append(" ORDER BY ").append(order);
                }
            }
        }
    }

    public static <ID, F extends BaseNomenclatureFilterDTO<ID>> void appendNameSearchQuery(F filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        String name = filter.getName();
        if (StringUtils.hasText(name)) {
            queryBuilder.append(" AND LOWER(r.name) like LOWER(:name) ");
            queryParameters.put("name", "%" + name + "%");
        }
    }

    public static <ID, F extends BaseNomenclatureFilterDTO<ID>> void appendActiveSearchQuery(F filter, StringBuilder queryBuilder, Map<String, Object> queryParameters) {
        Boolean isActive = filter.getIsActive();
        if (Objects.nonNull(isActive)) {
            queryBuilder.append(" AND r.active = :active ");
            queryParameters.put("active", isActive ? 1 : 0);
        }
    }
}
