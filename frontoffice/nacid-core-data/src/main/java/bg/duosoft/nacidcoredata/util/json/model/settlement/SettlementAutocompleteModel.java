package bg.duosoft.nacidcoredata.util.json.model.settlement;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor(staticName = "newInstance")
public class SettlementAutocompleteModel implements Serializable {
    private String id;
    private String name;
    private String optionName;
    private String selectedOptionValue;
    private Boolean isActive;
}