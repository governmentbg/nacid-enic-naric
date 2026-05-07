
package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum DocCorrespondentType {
//    WithoutType(0, "0"),
    Correspondent(1, "correspondent"),
    Applicant(2, "applicant"),
    Representative(3, "representative");

    DocCorrespondentType(int value, String alias) {
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
    static DocCorrespondentType fromAlias(Object object) {
        String alias = String.valueOf(object);
        return Stream.of(DocCorrespondentType.values())
                .filter(state -> state.alias.equals(alias) || String.valueOf(state.value).equals(alias))
                .findFirst()
                .get();
    }
}