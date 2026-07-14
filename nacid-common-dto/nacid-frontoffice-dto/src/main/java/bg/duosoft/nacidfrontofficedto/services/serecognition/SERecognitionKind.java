package bg.duosoft.nacidfrontofficedto.services.serecognition;

public enum SERecognitionKind {
    CERTIFICATE("REC", "Удостоверение за средно образование"),
    VERIFICATION_LETTER("SECR", "Уверение за средно образование"),
    OFFICIAL_NOTE("SEON", "Служебна бележка за средно образование");


    private final String code;
    private final String label;

    public String code() {
        return code;
    }
    public String label() {
        return label;
    }

    SERecognitionKind(String code, String label) {
        this.code = code;
        this.label = label;
    }
}
