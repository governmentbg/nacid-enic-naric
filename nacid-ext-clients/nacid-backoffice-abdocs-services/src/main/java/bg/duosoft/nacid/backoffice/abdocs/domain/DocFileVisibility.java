package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum DocFileVisibility {
    PrivateAttachedFile(1, "privateAttachedFile"), // Вътрешен
    PublicAttachedFile(2, "publicAttachedFile"); // Публичен

    DocFileVisibility(int value, String alias) {
        this.value = value;
        this.alias = alias;
    }

    private int value;
    private String alias;

    @JsonValue
    public int value() {
        return value;
    }

    public String alias() {
        return this.alias;
    }


    @JsonCreator
    static DocFileVisibility fromAlias(String alias) {
        return Stream.of(DocFileVisibility.values())
                .filter(state -> state.alias.equals(alias) || String.valueOf(state.value).equals(alias))
                .findFirst()
                .get();
    }

}
