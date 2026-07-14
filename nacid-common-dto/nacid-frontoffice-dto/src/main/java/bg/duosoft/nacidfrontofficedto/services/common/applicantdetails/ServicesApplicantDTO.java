package bg.duosoft.nacidfrontofficedto.services.common.applicantdetails;

import bg.duosoft.nacidfrontofficedto.person.ApplicantType;
import bg.duosoft.nacidfrontofficedto.person.CompanyDTO;
import bg.duosoft.nacidfrontofficedto.person.NaturalPersonDTO;
import bg.duosoft.nacidfrontofficedto.person.UniversityDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 17:10
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicesApplicantDTO {

    private ApplicantType applicantType;
    private NaturalPersonDTO naturalPerson;
    private CompanyDTO company;
    private UniversityDTO university;
}
