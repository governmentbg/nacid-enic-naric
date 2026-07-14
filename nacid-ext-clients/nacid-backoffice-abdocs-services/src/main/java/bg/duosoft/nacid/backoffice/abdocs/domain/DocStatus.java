package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum DocStatus {
    Draft(1, "draft"),
    Processed(3, "processed"),
    Finished(4, "finished"),
    Canceled(5, "canceled"),
    Archived(6, "archived"),
    Deleted(7, "deleted"),
    Unprocessed(8, "unprocessed"),
    Hidden(9, "hidden");

    DocStatus(int value, String alias) {
        this.value = value;
        this.alias = alias;
    }

    @JsonValue
    private final int value;
    private final String alias;

    public int value() {
        return value;
    }

    public String alias(){
        return alias;
    }

    @JsonCreator
    public static DocStatus fromAlias(String alias){
        return Stream.of(values()).filter(ds -> ds.alias.equals(alias) || String.valueOf(ds.value).equals(alias)).findFirst().orElse(null);
    }

}