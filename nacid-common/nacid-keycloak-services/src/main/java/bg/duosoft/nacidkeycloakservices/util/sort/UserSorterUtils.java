package bg.duosoft.nacidkeycloakservices.util.sort;

import bg.duosoft.nacidfrontofficedto.utils.constants.UserSortFields;

public class UserSorterUtils {

    public static String getQuerySortField(String sort){
        String sortQuery = switch (sort){
            case UserSortFields.NAME ->  "u.first_name";
            case UserSortFields.USERNAME -> "u.username";
            case UserSortFields.EMAIL -> "u.email";
            case UserSortFields.DATE_CREATED -> "u.created_timestamp";
            default ->  "u.first_name";
        };
        return sortQuery;
    }
}
