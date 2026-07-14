package bg.duosoft.nacid.backoffice.core.data.domain.rest;

public interface Sortable {
    String ASC_ORDER = "ASC";
    String DESC_ORDER = "DESC";

    String getOrder();

    String getOrderBy();
}
