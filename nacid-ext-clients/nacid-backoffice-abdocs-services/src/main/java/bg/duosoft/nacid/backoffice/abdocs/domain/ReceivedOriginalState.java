package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum ReceivedOriginalState {

    WaitingForOriginal(1, "waitingForOriginal"), // В изчакване
    ReceivedOriginal(2, "receivedOriginal"), // Получен
    NotWaitingForOriginal(3, "notWaitingForOriginal"); // Не чака оригинал

    ReceivedOriginalState(int value, String alias) {
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
    static ReceivedOriginalState fromAlias(String alias) {
        return Stream.of(ReceivedOriginalState.values())
                .filter(state -> state.alias.equals(alias) || String.valueOf(state.value).equals(alias))
                .findFirst()
                .get();
    }

}