package bg.duosoft.email.nacidemailproducer.filter;

public interface Sortable {
    String ASC_ORDER = "ASC";
    String DESC_ORDER = "DESC";

    String getOrder();

    String getOrderBy();
}
