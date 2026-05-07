package bg.duosoft.nacidfrontofficedto.qualassess;

import lombok.Data;

@Data
public class CountryRegionDTO {
    private Integer id;
    private String name;
    private String nameEn;
    private Boolean isActive;
}
