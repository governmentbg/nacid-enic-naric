package bg.duosoft.nacidfrontofficedto.qualassess;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CountryDetailsExtendedDTO extends CountryDetailsDTO {
    private List<CountryQFDTO> countriesQF;
}
