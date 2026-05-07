package bg.duosoft.nacidservicesbe.mapper.common.application;

import bg.duosoft.nacidcoredata.mapper.nomenclature.DocumentReceiveMethodMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationDocumentReceiveMethodDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationDocumentReceiveMethodEntity;
import bg.duosoft.nacidservicesbe.mapper.common.address.ReceiverAddressMapper;
import bg.duosoft.nacidservicesbe.utils.NomenclatureConstants;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.05.2024
 * Time: 15:50
 */
@Mapper(componentModel = "spring", uses = {
        ReferenceDataMapper.class,
        DocumentReceiveMethodMapper.class,
        ReceiverAddressMapper.class,
        IntegerToBooleanMapper.class,
})
public abstract class ApplicationPaperElectronicDocumentReceiveMethodMapper extends BaseObjectMapper<ApplicationDocumentReceiveMethodEntity, ApplicationDocumentReceiveMethodDTO> {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentReceiveMethod", source = "resultReceive")
    @Mapping(target = "documentRecipientAddress", source = "receiverAddress")
    @Mapping(target = "certificateReceiveForm.pk.id", source = "resultReceive.certificateReceiveFormCode")
    @Mapping(target = "certificateReceiveForm.pk.domain", expression = "java(bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDomain.CERTIFICATE_RECEIVE_FORM.name())")
    public abstract ApplicationDocumentReceiveMethodEntity toEntity(ApplicationDocumentReceiveMethodDTO applicationDocumentReceiveMethodDTO);

    @InheritInverseConfiguration
    public abstract ApplicationDocumentReceiveMethodDTO toDto(ApplicationDocumentReceiveMethodEntity applicationDocumentReceiveMethodEntity);


    @Named("resultReceiveElectronicMapping")
    public ApplicationDocumentReceiveMethodDTO toDocReceiveElectronicDto(List<ApplicationDocumentReceiveMethodEntity> list){
        if(list != null && list.size() >0){
            Optional<ApplicationDocumentReceiveMethodEntity> electronicMethod = list.stream()
                    .filter(receiveMethod -> receiveMethod.getCertificateReceiveForm() != null && receiveMethod.getCertificateReceiveForm().getPk().getId().equals(NomenclatureConstants.CERTIFICATE_RECEIVE_FORM_ELECTRONIC)).findFirst();
            if(electronicMethod.isPresent()) {
                return toDto(electronicMethod.get());
            }
        }
        return null;
    }

    @Named("resultReceivePaperMapping")
    public ApplicationDocumentReceiveMethodDTO toDocReceivePaperDto(List<ApplicationDocumentReceiveMethodEntity> list){
        if(list != null && list.size() >0){
            Optional<ApplicationDocumentReceiveMethodEntity> paperMethod = list.stream()
                    .filter(receiveMethod -> receiveMethod.getCertificateReceiveForm() != null && receiveMethod.getCertificateReceiveForm().getPk().getId().equals(NomenclatureConstants.CERTIFICATE_RECEIVE_FORM_PAPER)).findFirst();
            if(paperMethod.isPresent()) {
                return toDto(paperMethod.get());
            }
        }
        return null;
    }

    @Named("certificateReceiveFormsMapping")
    public List<String> toCertReceiveFormsDto(List<ApplicationDocumentReceiveMethodEntity> list){
        if(list != null && list.size() >0){
            return list.stream()
                    .filter(receiveMethod -> receiveMethod.getCertificateReceiveForm() != null)
                    .map(receiveMethod -> receiveMethod.getCertificateReceiveForm().getPk().getId()).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public List<ApplicationDocumentReceiveMethodEntity> createListFromDtoFields(CommonApplicantDetailsDTO dto){
        List<ApplicationDocumentReceiveMethodEntity> entityList = new ArrayList<>();
        if(dto != null && dto.getCertificateReceiveForms() != null && !dto.getCertificateReceiveForms().isEmpty()){
            if(dto.getCertificateReceiveForms().stream().filter(certForm -> certForm.equals(NomenclatureConstants.CERTIFICATE_RECEIVE_FORM_ELECTRONIC)).findFirst().isPresent() && dto.getResultReceiveElectronic() != null ){
                entityList.add(toEntity(dto.getResultReceiveElectronic()));
            }
            if(dto.getCertificateReceiveForms().stream().filter(certForm -> certForm.equals(NomenclatureConstants.CERTIFICATE_RECEIVE_FORM_PAPER)).findFirst().isPresent() && dto.getResultReceivePaper() != null ){
                entityList.add(toEntity(dto.getResultReceivePaper()));
            }
        }
        return entityList;
    }
}
