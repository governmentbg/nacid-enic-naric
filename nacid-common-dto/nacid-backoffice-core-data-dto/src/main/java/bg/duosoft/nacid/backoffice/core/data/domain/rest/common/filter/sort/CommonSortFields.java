package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.filter.sort;

import java.util.HashMap;
import java.util.Map;

public class CommonSortFields {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ORIGINAL_NAME = "originalName";
    public static final String CONTACT_PERSON = "contactPerson";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";
    public static final String POST_CODE = "postCode";
    public static final String PHONE = "phone";
    public static final String FAX = "fax";
    public static final String COUNTRY = "country";
    public static final String CITY = "city";
    public static final String POST_BOX = "postBox";
    public static final String SETTLEMENT = "settlement";
    public static final String ADDRESS_TYPE = "addressType";

    public static Map<String, String> sorterColumnMap() {
        Map<String, String> map = new HashMap<>();
        map.put(ID, "r.id");
        map.put(NAME, "r.name");
        map.put(ORIGINAL_NAME, "r.originalName");
        map.put(CONTACT_PERSON, "r.contactPerson");
        map.put(EMAIL, "r.email");
        map.put(ADDRESS, "r.address.id");
        map.put(POST_CODE, "r.postCode");
        map.put(PHONE, "r.phone");
        map.put(FAX, "r.fax");
        map.put(COUNTRY, "r.country.id");
        map.put(CITY, "r.city");
        map.put(POST_BOX, "r.postBox");
        map.put(SETTLEMENT, "r.settlement.id");
        map.put(ADDRESS_TYPE, "r.addressType.pk.id");
        return map;
    }
}
