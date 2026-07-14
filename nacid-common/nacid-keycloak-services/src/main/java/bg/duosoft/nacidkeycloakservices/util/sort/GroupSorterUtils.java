package bg.duosoft.nacidkeycloakservices.util.sort;

import bg.duosoft.nacidfrontofficedto.utils.constants.GroupSortFields;

import java.util.HashMap;
import java.util.Map;

public class GroupSorterUtils {

    public static String getQuerySortField(String sort){
        String sortQuery = switch (sort){
            case GroupSortFields.NAME -> "g.name";
            default -> "g.name";
        };
        return sortQuery;
    }
}
