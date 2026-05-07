package bg.duosoft.nacidfrontofficedto.qualassess;

import lombok.Data;

import java.util.List;

@Data
public class CountryQFDTO {
    private String name;
    private String nameEn;
    private String nameNative;
    private String description;
    private String descriptionEn;
    private Integer level;
    private String credits;
    private String duration;
    private EQFLevelDTO eqfLevel;
    private BolognaCycleDTO bolognaCycle;
    private BGLevelDTO bgLevel;
    private List<CountryQFLevelDTO> levels;
    private Integer detailsTablesIndex;
}
