package bg.duosoft.nacidservicesbe.mapper.regprof;

import bg.duosoft.nacidcoredata.mapper.nomenclature.CountryMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApplicantDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationDocumentReceiveMethodEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidservicesbe.mapper.common.applicantdetails.BaseApplicantDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.common.application.ApplicationPaperElectronicDocumentReceiveMethodMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.12.2022
 * Time: 11:38
 */
@Mapper(componentModel = "spring", config = BaseApplicantDetailsMapper.class, uses = {
        ReferenceDataMapper.class,
        CountryMapper.class,
})
public abstract class RegprofApplicantDetailsMapper extends BaseObjectMapper<ApplicationEntity, RegprofApplicantDetailsDTO> {

    @Autowired
    private ApplicationPaperElectronicDocumentReceiveMethodMapper applicationPaperElectronicDocumentReceiveMethodMapper;

    @InheritConfiguration(name = "baseApplicantDetailsMapping")
    @Mapping(target = "diffDiplomaNamesFlag", source = "qualificationNamesDifferent")
    @Mapping(target = "diffDiplomaNames.firstName", source = "qualificationNames.firstName")
    @Mapping(target = "diffDiplomaNames.secondName", source = "qualificationNames.middleName")
    @Mapping(target = "diffDiplomaNames.lastName", source = "qualificationNames.lastName")
    @Mapping(target = "diffDiplomaNames.civilId", source = "qualificationNames.personalId")
    @Mapping(target = "diffDiplomaNames.civilIdTypeCode", source = "qualificationNames.personalIdType.code")
    @Mapping(target = "diffDiplomaNames.foreignIdType", source = "qualificationNames.foreignerIdentifierKind")
    @Mapping(target = "diffDiplomaNames.foreignIdCountry", source = "qualificationNames.foreignerIdentifierCountry")
    @Mapping(target = "representativeCompanyId", source = "representativeCompanyIdentifier")

    @Mapping(target = "applicationDocumentReceiveMethods", source = ".")
    public abstract ApplicationEntity toEntity(RegprofApplicantDetailsDTO applicantDetailsDTO);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "applicantHasRepresentative", expression = "java(applicationEntity.getRepresentative() != null)")
    @Mapping(target = "qualificationNames.personalIdType",
            expression = "java(applicantDiplomaNamesEntity != null ? bg.duosoft.nacidfrontofficedto.person.PersonalIdentifierType.fromCode(applicantDiplomaNamesEntity.getCivilIdTypeCode()) : null)")
    public abstract RegprofApplicantDetailsDTO toDto(ApplicationEntity applicationEntity);

    @AfterMapping
    public void afterToEntity(@MappingTarget ApplicationEntity entity, RegprofApplicantDetailsDTO dto){
        if(entity.getDiffDiplomaNamesFlag() == 0){
            entity.setDiffDiplomaNames(null);
        }
    }

    @AfterMapping
    public void afterToDto(@MappingTarget RegprofApplicantDetailsDTO dto, ApplicationEntity entity){
        dto.setResultReceiveElectronic(applicationPaperElectronicDocumentReceiveMethodMapper.toDocReceiveElectronicDto(entity.getApplicationDocumentReceiveMethods()));
        dto.setResultReceivePaper(applicationPaperElectronicDocumentReceiveMethodMapper.toDocReceivePaperDto(entity.getApplicationDocumentReceiveMethods()));
        dto.setCertificateReceiveForms(applicationPaperElectronicDocumentReceiveMethodMapper.toCertReceiveFormsDto(entity.getApplicationDocumentReceiveMethods()));
    }

    public List<ApplicationDocumentReceiveMethodEntity> toDocReceiveEntityList(RegprofApplicantDetailsDTO dto){
        return applicationPaperElectronicDocumentReceiveMethodMapper.createListFromDtoFields(dto);
    }
}
