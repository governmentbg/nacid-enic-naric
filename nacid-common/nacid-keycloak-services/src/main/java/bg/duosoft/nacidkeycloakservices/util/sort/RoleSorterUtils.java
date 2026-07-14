package bg.duosoft.nacidkeycloakservices.util.sort;

import bg.duosoft.nacidfrontofficedto.utils.constants.RoleSortFields;

public class RoleSorterUtils {

    public static String getQuerySortField(String sort){
        String sortQuery = switch (sort){
            case RoleSortFields.NAME ->  "r.name";
            case RoleSortFields.DESCRIPTION ->  "r.description";
            default ->  "r.name";
        };
        return sortQuery;
    }
}
