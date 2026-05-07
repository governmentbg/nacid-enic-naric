package bg.duosoft.nacidservicesbe.mapper.common.applicantdetails;

import bg.duosoft.nacidfrontofficedto.person.ApplicantType;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.ServicesApplicantDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.PersonEntity;
import bg.duosoft.nacidservicesbe.mapper.common.person.CompanyMapper;
import bg.duosoft.nacidservicesbe.mapper.common.person.NaturalPersonMapper;
import bg.duosoft.nacidservicesbe.mapper.common.person.UniversityMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 17:26
 */
@Mapper(componentModel = "spring")
public abstract class ServicesApplicantMapper extends BaseObjectMapper<PersonEntity, ServicesApplicantDTO> {

    @Autowired
    private NaturalPersonMapper naturalPersonMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private UniversityMapper universityMapper;

    public PersonEntity toEntity(ServicesApplicantDTO applicant){
        if(applicant != null && applicant.getApplicantType() != null) {
            if (applicant.getApplicantType().equals(ApplicantType.NATURAL_PERSON)) {
                return naturalPersonMapper.toEntity(applicant.getNaturalPerson());
            } else if (applicant.getApplicantType().equals(ApplicantType.COMPANY)) {
                return companyMapper.toEntity(applicant.getCompany());
            } else if(applicant.getApplicantType().equals(ApplicantType.UNIVERSITY)){
                return universityMapper.toEntity(applicant.getUniversity());
            }
        }
        return null;
    }

    public ServicesApplicantDTO toDto(PersonEntity person) {
        ServicesApplicantDTO servicesApplicantDTO = new ServicesApplicantDTO();
        if(person != null) {
            ApplicantType applicantType = ApplicantType.fromLegalTypeAndNatureCodes(person.getLegalTypeCode(), person.getLegalNatureTypeCode());
            servicesApplicantDTO.setApplicantType(applicantType);
            if (applicantType.equals(ApplicantType.NATURAL_PERSON)) {
                servicesApplicantDTO.setNaturalPerson(naturalPersonMapper.toDto(person));
            } else if (applicantType.equals(ApplicantType.COMPANY)) {
                servicesApplicantDTO.setCompany(companyMapper.toDto(person));
            } else if(applicantType.equals(ApplicantType.UNIVERSITY)) {
                servicesApplicantDTO.setUniversity(universityMapper.toDto(person));
            }
        } else {
            servicesApplicantDTO.setApplicantType(ApplicantType.NATURAL_PERSON);
        }
        return servicesApplicantDTO;
    }


}
