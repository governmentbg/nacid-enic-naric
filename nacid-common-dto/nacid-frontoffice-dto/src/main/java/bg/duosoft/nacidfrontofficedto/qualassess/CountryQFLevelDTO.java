package bg.duosoft.nacidfrontofficedto.qualassess;

import lombok.Data;

@Data
public class CountryQFLevelDTO {
    private Integer id;
    private Integer eqfLevel;
    private String name;
    private String nameEn;
    private String nameNative;
    private Integer displayOrder;
    private String duration;
    private String credits;
}
