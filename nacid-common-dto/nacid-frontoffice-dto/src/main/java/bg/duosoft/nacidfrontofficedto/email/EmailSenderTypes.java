package bg.duosoft.nacidfrontofficedto.email;

import lombok.Getter;

@Getter
public enum EmailSenderTypes {
    FORGOTTEN_PASSWORD(true),
    FORGOTTEN_USERNAME(true),
    ACTIVATION_LINK(true),
    UPDATE_EMAIL(false);

    EmailSenderTypes(Boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    private final Boolean isAnonymous;
}
