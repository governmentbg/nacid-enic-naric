package bg.duosoft.nacid.opendata.dto;

import lombok.Data;

/**
 * User: Georgi
 * Date: 8.9.2020 г.
 * Time: 19:08
 */
@Data
public class Tsv2JsonRequest extends OpenDataRequest {
    private byte[] data;
}
