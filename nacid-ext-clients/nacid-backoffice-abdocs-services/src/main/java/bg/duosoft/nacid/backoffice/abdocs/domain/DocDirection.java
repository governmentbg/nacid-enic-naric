package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum DocDirection {
    Incoming(1, "incoming"),
    Internal(2, "internal"),
    Outgoing(3, "outgoing"),
    InternalOutgoing(4, "internalOutgoing");

    DocDirection(int value, String alias) {
        this.value = value;
        this.alias = alias;
    }

    private final int value;
    private final String alias;

    @JsonValue
    public int value() {
        return this.value;
    }

    public String alias() {
        return this.alias;
    }

    @JsonCreator
    static DocDirection fromAlias(String alias) {
        return Stream.of(DocDirection.values())
                .filter(state -> state.alias.equals(alias) || String.valueOf(state.value).equals(alias))
                .findFirst()
                .get();
    }

}