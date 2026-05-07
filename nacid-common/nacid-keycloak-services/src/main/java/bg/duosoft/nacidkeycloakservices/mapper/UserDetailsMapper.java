package bg.duosoft.nacidkeycloakservices.mapper;

import bg.duosoft.nacidfrontofficedto.address.ContactAddressDTO;
import bg.duosoft.nacidfrontofficedto.address.ReceiverAddressDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.*;
import bg.duosoft.nacidfrontofficedto.person.PersonalIdentifierType;
import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import bg.duosoft.nacidfrontofficedto.user.UserRepresentativeType;
import bg.duosoft.nacidkeycloakservices.util.attribute.AttributesUtil;
import bg.duosoft.nacidkeycloakservices.util.attribute.Extractor;
import bg.duosoft.nacidkeycloakservices.util.attribute.UserAttributes;
import bg.duosoft.nacidkeycloakservices.util.convert.BooleanUtils;
import bg.duosoft.nacidkeycloakservices.util.convert.DateUtils;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.*;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 14.07.2022
 * Time: 20:06
 */
@Mapper(componentModel = "spring")
public abstract class UserDetailsMapper {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    public abstract NacidUserDetailsDTO toDto(UserRepresentation userRepresentation);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    public abstract List<NacidUserDetailsDTO> toDtoList(List<UserRepresentation> userRepresentations);

    @InheritInverseConfiguration
    public abstract UserRepresentation fromDto(NacidUserDetailsDTO userDetails);

    @AfterMapping
    protected void afterToDto(UserRepresentation source, @MappingTarget NacidUserDetailsDTO target) {
        Map<String, List<String>> attributes = source.getAttributes();
        if (Objects.nonNull(attributes)) {
            target.setMiddleName(Extractor.firstOrNull(attributes.get(UserAttributes.MIDDLE_NAME)));
            target.setPersonalId(Extractor.firstOrNull(attributes.get(UserAttributes.PERSONAL_ID)));
            target.setPersonalNacidId(Extractor.firstOrNull(attributes.get(UserAttributes.PERSONAL_NACID_ID)));
            String personalIdType = Extractor.firstOrNull(attributes.get(UserAttributes.PERSONAL_ID_TYPE));
            if(personalIdType != null && StringUtils.hasText(personalIdType)) {
                target.setPersonalIdType(PersonalIdentifierType.valueOf(personalIdType));
            }
            target.setCitizenship(new CountryDTO());
            target.getCitizenship().setId(Extractor.firstOrNull(attributes.get(UserAttributes.CITIZENSHIP)));
            target.setBirthCountry(new CountryDTO());
            target.getBirthCountry().setId(Extractor.firstOrNull(attributes.get(UserAttributes.BIRTH_COUNTRY)));
            target.setBirthPlace(Extractor.firstOrNull(attributes.get(UserAttributes.BIRTH_PLACE)));
            target.setBirthSettlement(new SettlementDTO());
            target.getBirthSettlement().setId(Extractor.firstOrNull(attributes.get(UserAttributes.BIRTH_SETTLEMENT)));
            target.setDateOfBirth(DateUtils.parseLocalDate(Extractor.firstOrNull(attributes.get(UserAttributes.DATE_OF_BIRTH))));

            target.setForeignerIdentifierKind(new ReferenceDataDTO());
            target.getForeignerIdentifierKind().setId(Extractor.firstOrNull(attributes.get(UserAttributes.FOREIGNER_IDENTIFIER_KIND)));
            target.getForeignerIdentifierKind().setDomainName(ReferenceDataDomain.FOREIGN_IDENTIFIER_TYPE.name());
            target.setForeignerIdentifierCountry(new CountryDTO());
            target.getForeignerIdentifierCountry().setId(Extractor.firstOrNull(attributes.get(UserAttributes.FOREIGNER_IDENTIFIER_COUNTRY)));
            target.setHumanitarianStatus(new ReferenceDataDTO());
            target.getHumanitarianStatus().setDomainName(ReferenceDataDomain.HUMANITARIAN_STATUS.name());
            target.getHumanitarianStatus().setId(Extractor.firstOrNull(attributes.get(UserAttributes.HUMANITARIAN_STATUS)));
            target.setTitle(Extractor.firstOrNull(attributes.get(UserAttributes.TITLE)));

            Boolean isRepresentative = BooleanUtils.convertStringToBoolean(Extractor.firstOrNull(attributes.get(UserAttributes.IS_REPRESENTATIVE)));
            target.setIsRepresentative(Objects.nonNull(isRepresentative) ? isRepresentative : false);
            String representativeType = Extractor.firstOrNull(attributes.get(UserAttributes.REPRESENTATIVE_TYPE));
            if(representativeType != null && StringUtils.hasText(representativeType)) {
                target.setRepresentativeType(UserRepresentativeType.valueOf(representativeType));
            }
            target.setRepresentedUniversity(Extractor.firstOrNull(attributes.get(UserAttributes.REPRESENTED_UNIVERSITY)));
            target.setRepresentedCompany(Extractor.firstOrNull(attributes.get(UserAttributes.REPRESENTED_COMPANY)));
            target.setRepresentativeCapacity(Extractor.firstOrNull(attributes.get(UserAttributes.REPRESENTATIVE_CAPACITY)));
            target.setResultReceive(new DocumentReceiveMethodDTO());
            target.getResultReceive().setId(Extractor.firstOrNull(attributes.get(UserAttributes.RESULT_RECEIVE)));

            if (Objects.isNull(target.getContactAddress())) {
                target.setContactAddress(new ContactAddressDTO());
            }

            target.getContactAddress().setCountry(new CountryDTO());
            target.getContactAddress().getCountry().setId(Extractor.firstOrNull(attributes.get(UserAttributes.CA_COUNTRY)));
            target.getContactAddress().setSettlement(new SettlementDTO());
            target.getContactAddress().getSettlement().setId(Extractor.firstOrNull(attributes.get(UserAttributes.CA_SETTLEMENT)));
            target.getContactAddress().setCity(Extractor.firstOrNull(attributes.get(UserAttributes.CA_CITY)));
            target.getContactAddress().setPostCode(Extractor.firstOrNull(attributes.get(UserAttributes.CA_POST_CODE)));
            target.getContactAddress().setAddress(Extractor.firstOrNull(attributes.get(UserAttributes.CA_ADDRESS)));
            target.getContactAddress().setPhone(Extractor.firstOrNull(attributes.get(UserAttributes.CA_PHONE)));
            target.getContactAddress().setFax(Extractor.firstOrNull(attributes.get(UserAttributes.CA_FAX)));
            target.getContactAddress().setEmail(Extractor.firstOrNull(attributes.get(UserAttributes.CA_EMAIL)));
            target.getContactAddress().setPostBox(Extractor.firstOrNull(attributes.get(UserAttributes.CA_POST_BOX)));

            if (Objects.isNull(target.getReceiverAddress())) {
                target.setReceiverAddress(new ReceiverAddressDTO());
            }

            target.getReceiverAddress().setCountry(new CountryDTO());
            target.getReceiverAddress().getCountry().setId(Extractor.firstOrNull(attributes.get(UserAttributes.RA_COUNTRY)));
            target.getReceiverAddress().setSettlement(new SettlementDTO());
            target.getReceiverAddress().getSettlement().setId(Extractor.firstOrNull(attributes.get(UserAttributes.RA_SETTLEMENT)));
            target.getReceiverAddress().setCity(Extractor.firstOrNull(attributes.get(UserAttributes.RA_CITY)));
            target.getReceiverAddress().setPostCode(Extractor.firstOrNull(attributes.get(UserAttributes.RA_POST_CODE)));
            target.getReceiverAddress().setAddress(Extractor.firstOrNull(attributes.get(UserAttributes.RA_ADDRESS)));
            target.getReceiverAddress().setPhone(Extractor.firstOrNull(attributes.get(UserAttributes.RA_PHONE)));
            target.getReceiverAddress().setName(Extractor.firstOrNull(attributes.get(UserAttributes.RA_NAME)));

            target.setNacidEmployeePosition(Extractor.firstOrNull(attributes.get(UserAttributes.NACID_EMPLOYEE_POSITION)));
        }
    }

    @AfterMapping
    protected void afterFromDto(NacidUserDetailsDTO source, @MappingTarget UserRepresentation target){
        if(source.getPassword() != null && !source.getPassword().isEmpty()) {
            target.setCredentials(Collections.singletonList(mapPasswordToCredentialRepresentation(source.getPassword())));
        }

        Map<String, List<String>> attributes = target.getAttributes();
        if (Objects.isNull(attributes)) {
            attributes = new HashMap<>();
        }

        AttributesUtil.setAttribute(attributes, UserAttributes.MIDDLE_NAME, source.getMiddleName());
        AttributesUtil.setAttribute(attributes, UserAttributes.PERSONAL_ID, source.getPersonalId());
        AttributesUtil.setAttribute(attributes, UserAttributes.PERSONAL_NACID_ID, source.getPersonalNacidId());
        if(source.getPersonalIdType() != null) {
            AttributesUtil.setAttribute(attributes, UserAttributes.PERSONAL_ID_TYPE, source.getPersonalIdType().name());
        }
        if (source.getCitizenship() != null && StringUtils.hasText(source.getCitizenship().getId())) {
            AttributesUtil.setAttribute(attributes, UserAttributes.CITIZENSHIP, source.getCitizenship().getId());
        }
        if (source.getBirthCountry() != null && StringUtils.hasText(source.getBirthCountry().getId())) {
            AttributesUtil.setAttribute(attributes, UserAttributes.BIRTH_COUNTRY, source.getBirthCountry().getId());
        }
        AttributesUtil.setAttribute(attributes, UserAttributes.BIRTH_PLACE, source.getBirthPlace());
        if(source.getBirthSettlement() != null && StringUtils.hasText(source.getBirthSettlement().getId())){
            AttributesUtil.setAttribute(attributes, UserAttributes.BIRTH_SETTLEMENT, source.getBirthSettlement().getId());
        }
        AttributesUtil.setAttribute(attributes, UserAttributes.DATE_OF_BIRTH, DateUtils.localDateToString(source.getDateOfBirth()));
        if(source.getForeignerIdentifierKind() != null && StringUtils.hasText(source.getForeignerIdentifierKind().getId())) {
            AttributesUtil.setAttribute(attributes, UserAttributes.FOREIGNER_IDENTIFIER_KIND, source.getForeignerIdentifierKind().getId());
        }
        if(source.getForeignerIdentifierCountry() != null && StringUtils.hasText(source.getForeignerIdentifierCountry().getId())) {
            AttributesUtil.setAttribute(attributes, UserAttributes.FOREIGNER_IDENTIFIER_COUNTRY, source.getForeignerIdentifierCountry().getId());
        }
        if(source.getHumanitarianStatus() != null && StringUtils.hasText(source.getHumanitarianStatus().getId())){
            AttributesUtil.setAttribute(attributes, UserAttributes.HUMANITARIAN_STATUS, source.getHumanitarianStatus().getId());
        }
        AttributesUtil.setAttribute(attributes, UserAttributes.TITLE, source.getTitle());
        AttributesUtil.setAttribute(attributes, UserAttributes.IS_REPRESENTATIVE, Objects.nonNull(source.getIsRepresentative()) ? Boolean.toString(source.getIsRepresentative()) : "");
        if(source.getRepresentativeType() != null) {
            AttributesUtil.setAttribute(attributes, UserAttributes.REPRESENTATIVE_TYPE, source.getRepresentativeType().name());
        }
        AttributesUtil.setAttribute(attributes, UserAttributes.REPRESENTED_UNIVERSITY, source.getRepresentedUniversity());
        AttributesUtil.setAttribute(attributes, UserAttributes.REPRESENTED_COMPANY, source.getRepresentedCompany());
        AttributesUtil.setAttribute(attributes, UserAttributes.REPRESENTATIVE_CAPACITY, source.getRepresentativeCapacity());
        if(source.getResultReceive() != null && StringUtils.hasText(source.getResultReceive().getId())) {
            AttributesUtil.setAttribute(attributes, UserAttributes.RESULT_RECEIVE, source.getResultReceive().getId());
        }

        if (Objects.nonNull(source.getContactAddress())) {
            if(source.getContactAddress().getCountry() != null && StringUtils.hasText(source.getContactAddress().getCountry().getId())) {
                AttributesUtil.setAttribute(attributes, UserAttributes.CA_COUNTRY, source.getContactAddress().getCountry().getId());
            }
            if(source.getContactAddress().getSettlement() != null && StringUtils.hasText(source.getContactAddress().getCountry().getId())){
                AttributesUtil.setAttribute(attributes, UserAttributes.CA_SETTLEMENT, source.getContactAddress().getSettlement().getId());
            }
            AttributesUtil.setAttribute(attributes, UserAttributes.CA_CITY, source.getContactAddress().getCity());
            AttributesUtil.setAttribute(attributes, UserAttributes.CA_POST_CODE, source.getContactAddress().getPostCode());
            AttributesUtil.setAttribute(attributes, UserAttributes.CA_ADDRESS, source.getContactAddress().getAddress());
            AttributesUtil.setAttribute(attributes, UserAttributes.CA_PHONE, source.getContactAddress().getPhone());
            AttributesUtil.setAttribute(attributes, UserAttributes.CA_FAX, source.getContactAddress().getFax());
            AttributesUtil.setAttribute(attributes, UserAttributes.CA_EMAIL, source.getContactAddress().getEmail());
            AttributesUtil.setAttribute(attributes, UserAttributes.CA_POST_BOX, source.getContactAddress().getPostBox());
        }

        if (Objects.nonNull(source.getReceiverAddress())) {
            if(source.getReceiverAddress().getCountry() != null && StringUtils.hasText(source.getReceiverAddress().getCountry().getId())) {
                AttributesUtil.setAttribute(attributes, UserAttributes.RA_COUNTRY, source.getReceiverAddress().getCountry().getId());
            }
            if(source.getReceiverAddress().getSettlement() != null && StringUtils.hasText(source.getReceiverAddress().getCountry().getId())){
                AttributesUtil.setAttribute(attributes, UserAttributes.RA_SETTLEMENT, source.getReceiverAddress().getSettlement().getId());
            }
            AttributesUtil.setAttribute(attributes, UserAttributes.RA_CITY, source.getReceiverAddress().getCity());
            AttributesUtil.setAttribute(attributes, UserAttributes.RA_POST_CODE, source.getReceiverAddress().getPostCode());
            AttributesUtil.setAttribute(attributes, UserAttributes.RA_ADDRESS, source.getReceiverAddress().getAddress());
            AttributesUtil.setAttribute(attributes, UserAttributes.RA_PHONE, source.getReceiverAddress().getPhone());
            AttributesUtil.setAttribute(attributes, UserAttributes.RA_NAME, source.getReceiverAddress().getName());
        }

        AttributesUtil.setAttribute(attributes, UserAttributes.NACID_EMPLOYEE_POSITION, source.getNacidEmployeePosition());

        target.setAttributes(attributes);
    }

    public CredentialRepresentation mapPasswordToCredentialRepresentation(String password){
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);
        return credential;
    }


}
