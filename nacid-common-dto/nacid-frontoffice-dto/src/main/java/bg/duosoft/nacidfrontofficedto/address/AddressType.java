package bg.duosoft.nacidfrontofficedto.address;

import bg.duosoft.nacidfrontofficedto.person.PersonalIdentifierType;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.09.2022
 * Time: 15:02
 */
public enum AddressType {

    CONTACT_ADDRESS("CA"),
    COMPETENT_INSTITUTION_ADDRESS("CIA"),
    RECEIVER_ADDRESS("DA"),
    PERSON_ADDRESS("PA"),
    PROFESSIONAL_INSTITUTION_ADDRESS("PIA"),
    UNIVERSITY_ADDRESS("UA");

    private String code;

    public String getCode() {
        return code;
    }

    AddressType(String code) {
        this.code = code;
    }

    public static AddressType fromCode(String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }

        return Arrays.stream(AddressType.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }


}
