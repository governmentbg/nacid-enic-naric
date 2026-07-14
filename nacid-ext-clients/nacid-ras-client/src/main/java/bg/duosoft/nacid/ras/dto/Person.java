package bg.duosoft.nacid.ras.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

/**
 * User: ggeorgiev
 * Date: 22.10.2019 г.
 * Time: 18:51
 */
public class Person {
    private int type;
    private String firstName;
    private String middleName;
    private String lastName;
    private String firstNameAlt;
    private String middleNameAlt;
    private String lastNameAlt;
    private String uin;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Sofia")
    private LocalDate birthDate;
    private String citizenshipCode;
    private String birthPlaceCode;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstNameAlt() {
        return firstNameAlt;
    }

    public void setFirstNameAlt(String firstNameAlt) {
        this.firstNameAlt = firstNameAlt;
    }

    public String getMiddleNameAlt() {
        return middleNameAlt;
    }

    public void setMiddleNameAlt(String middleNameAlt) {
        this.middleNameAlt = middleNameAlt;
    }

    public String getLastNameAlt() {
        return lastNameAlt;
    }

    public void setLastNameAlt(String lastNameAlt) {
        this.lastNameAlt = lastNameAlt;
    }

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public String getBirthPlaceCode() {
        return birthPlaceCode;
    }

    public void setBirthPlaceCode(String birthPlaceCode) {
        this.birthPlaceCode = birthPlaceCode;
    }
}