package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum ConnectionType {
    AND("AND"),
    NOT("NOT");

    private final String code;

    public String code() {
        return code;
    }

    ConnectionType(String code) {
        this.code = code;
    }

    @JsonCreator
    static ConnectionType fromCode(String code) {
        return Stream.of(ConnectionType.values())
                .filter(state -> state.code.equals(code))
                .findFirst()
                .orElse(null);
    }

}
