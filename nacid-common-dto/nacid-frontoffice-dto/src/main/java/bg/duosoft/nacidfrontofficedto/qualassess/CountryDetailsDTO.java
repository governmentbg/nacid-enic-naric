package bg.duosoft.nacidfrontofficedto.qualassess;

import bg.duosoft.nacidfrontofficedto.nomenclature.BaseNomenclatureDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CountryDetailsDTO {
    private CountryDTO country;
    private BaseNomenclatureDTO region;
    private BaseNomenclatureDTO bolognaCompatibility;
    private String flagUrl;
    private String systemSummary;
    private String systemSummaryEn;
    private String eqfStatus;
    private String eqfDescription;
    private String eqfDescriptionEn;
    private String eqfDescriptionLong;
    private String eqfDescriptionLongEn;
    private String officialSources;
    private String creditsLabel;
    private Boolean isActive;
    private LocalDate lastUpdate;
    private Integer detailsTablesNumber;
    private String detailsButtonsTextsBg;
    private String detailsButtonsTextsEn;

}
