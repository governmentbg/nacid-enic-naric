package bg.duosoft.nacid.opendata.dto;

import lombok.Data;

import java.util.List;

/**
 * User: Georgi
 * Date: 8.9.2020 г.
 * Time: 19:08
 */
@Data
public class Tsv2JsonResponse extends OpenDataResponse {
    private List<List<String>> data;
}
