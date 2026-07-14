package bg.duosoft.nacidservicesbe.mapper.common.applicantdetails;

import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.RudiApplicantDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationDocumentReceiveMethodEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidservicesbe.mapper.common.application.ApplicationPaperElectronicDocumentReceiveMethodMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.11.2022
 * Time: 14:00
 */
@Mapper(componentModel = "spring", config = BaseApplicantDetailsMapper.class, uses = {
        ReferenceDataMapper.class,
        ApplicationPaperElectronicDocumentReceiveMethodMapper.class
})
public abstract class RudiApplicantDetailsMapper extends BaseObjectMapper<ApplicationEntity, RudiApplicantDetailsDTO> {

    @Autowired
    private ApplicationPaperElectronicDocumentReceiveMethodMapper applicationPaperElectronicDocumentReceiveMethodMapper;

    @InheritConfiguration(name = "baseApplicantDetailsMapping")
    @Mapping(target = "diffDiplomaNamesFlag", source = "diplomaNamesDifferent")
    @Mapping(target = "diffDiplomaNames.firstName", source = "diplomaNames.firstName")
    @Mapping(target = "diffDiplomaNames.secondName", source = "diplomaNames.middleName")
    @Mapping(target = "diffDiplomaNames.lastName", source = "diplomaNames.lastName")
    @Mapping(target = "representativeCompanyId", source = "representativeCompanyIdentifier")
    @Mapping(target = "applicationDocumentReceiveMethods", source = ".")
    public abstract ApplicationEntity toEntity(RudiApplicantDetailsDTO applicantDetailsDTO);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "applicantHasRepresentative", expression = "java(applicationEntity.getRepresentative() != null)")
    public abstract RudiApplicantDetailsDTO toDto(ApplicationEntity applicationEntity);

    @AfterMapping
    public void afterToEntity(@MappingTarget ApplicationEntity entity, RudiApplicantDetailsDTO dto){
        if(entity.getDiffDiplomaNamesFlag() == 0){
            entity.setDiffDiplomaNames(null);
        }
    }
    @AfterMapping
    public void afterToDto(@MappingTarget RudiApplicantDetailsDTO dto, ApplicationEntity entity){
        dto.setResultReceiveElectronic(applicationPaperElectronicDocumentReceiveMethodMapper.toDocReceiveElectronicDto(entity.getApplicationDocumentReceiveMethods()));
        dto.setResultReceivePaper(applicationPaperElectronicDocumentReceiveMethodMapper.toDocReceivePaperDto(entity.getApplicationDocumentReceiveMethods()));
        dto.setCertificateReceiveForms(applicationPaperElectronicDocumentReceiveMethodMapper.toCertReceiveFormsDto(entity.getApplicationDocumentReceiveMethods()));
    }

    public List<ApplicationDocumentReceiveMethodEntity> toDocReceiveEntityList(RudiApplicantDetailsDTO dto){
        return applicationPaperElectronicDocumentReceiveMethodMapper.createListFromDtoFields(dto);
    }
}
