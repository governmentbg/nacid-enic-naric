package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum CorrespondentType {
//    WithoutType(0, "0"),
    BulgarianCitizen(1, "bulgarianCitizen"),
    Foreigner(2, "foreigner"),
    LegalEntity(3, "legalEntity"),
    ForeignLegalEntity(4, "foreignLegalEntity");

    CorrespondentType(int value, String alias) {
        this.value = value;
        this.alias = alias;
    }

    private int value;
    private String alias;

    @JsonValue
    public int value() {
        return this.value;
    }

    public String alias() {
        return this.alias;
    }

    @JsonCreator
    static CorrespondentType fromAlias(Object object) {
        String alias = String.valueOf(object);
        return Stream.of(CorrespondentType.values())
                .filter(state -> state.alias.equals(alias) || String.valueOf(state.value).equals(alias))
                .findFirst()
                .get();
    }
}
