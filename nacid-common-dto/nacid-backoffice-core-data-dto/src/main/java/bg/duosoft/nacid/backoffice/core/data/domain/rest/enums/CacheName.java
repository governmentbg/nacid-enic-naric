package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

public enum CacheName {

    KEYCLOCK_USERS("KeycloakUsers");

    private final String code;

    public String code() {
        return code;
    }

    CacheName(String code) {
        this.code = code;
    }


}
