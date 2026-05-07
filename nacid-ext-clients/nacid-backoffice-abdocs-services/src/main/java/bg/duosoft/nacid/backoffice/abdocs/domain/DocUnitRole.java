package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DocUnitRole {
    From(1), // От
    To(2),// До
    ImportedBy(3),// Въвел
    MadeBy(4),// Изготвил
    CCopy(5), // Копие до
    Editors(9); // Съгласувал

    DocUnitRole(int value) {
        this.value = value;
    }

    private final int value;

    @JsonValue
    public int value() {
        return value;
    }

}
