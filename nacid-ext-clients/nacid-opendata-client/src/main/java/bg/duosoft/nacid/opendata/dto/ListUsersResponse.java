package bg.duosoft.nacid.opendata.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * User: Georgi
 * Date: 8.9.2020 г.
 * Time: 11:01
 */
@Data
public class ListUsersResponse extends OpenDataResponse{
    private List<OpenDataUser> users;
    @JsonProperty("total_records")
    private int totalRecords;
}
