package bg.duosoft.nacid.backoffice.core.data.mapper.fo.person;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacidfrontofficedto.person.ApplicantType;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.ServicesApplicantDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class FoApplicantPersonMapper {
    @Autowired
    private FoNaturalPersonMapper foNaturalPersonMapper;
    @Autowired
    private FoCompanyMapper foCompanyMapper;
    @Autowired
    private FoUniversityMapper foUniversityMapper;

    public abstract PersonDTO toApplicantPerson(CommonApplicantDetailsDTO applicantDetails);

    @AfterMapping
    protected void afterToApplicantPersonDto(CommonApplicantDetailsDTO applicantDetails, @MappingTarget PersonDTO boPerson) {
        if (Objects.nonNull(applicantDetails)) {
            ServicesApplicantDTO servicesApplicant = applicantDetails.getApplicant();
            if (Objects.nonNull(servicesApplicant)) {
                ApplicantType applicantType = servicesApplicant.getApplicantType();

                switch (applicantType) {
                    case NATURAL_PERSON -> foNaturalPersonMapper.overridePersonDto(servicesApplicant.getNaturalPerson(), boPerson);
                    case COMPANY -> foCompanyMapper.overridePersonDto(servicesApplicant.getCompany(), boPerson);
                    case UNIVERSITY -> foUniversityMapper.overridePersonDto(servicesApplicant.getUniversity(), boPerson);
                }
            }
        }
    }
}