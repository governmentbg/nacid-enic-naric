package bg.duosoft.nacid.ras.dto;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 22.10.2019 г.
 * Time: 18:48
 */
public class Applicant {
    private int correspondentType;
    private List<CorrespondentContact> correspondentContacts;
    private String firstName;
    private String middleName;
    private String lastName;
    private String uin;
    private String contactPhone;

    public int getCorrespondentType() {
        return correspondentType;
    }

    public void setCorrespondentType(int correspondentType) {
        this.correspondentType = correspondentType;
    }

    public List<CorrespondentContact> getCorrespondentContacts() {
        return correspondentContacts;
    }

    public void setCorrespondentContacts(List<CorrespondentContact> correspondentContacts) {
        this.correspondentContacts = correspondentContacts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}