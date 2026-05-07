package bg.duosoft.nacid.backoffice.core.data.util.abdocs;

import bg.duosoft.nacid.backoffice.abdocs.domain.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ExternalNomenclaturesMapDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ExternalNomenclaturesMapMapper;
import bg.duosoft.nacid.backoffice.core.data.util.common.PersonUtils;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import bg.duosoft.nacidshareddata.util.integer.IntegerUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AbdocsConverter {

    private static final int CORRESPONDENTS_OTHERS_GROUP_ID = 53;

    public static List<DocCorrespondent> createDocCorrespondents(ApplicationDTO application, List<ExternalNomenclaturesMapDTO> abdocsSettlementsMap) {
        if (Objects.isNull(application)) {
            return null;
        }

        PersonDTO applicant = application.getApplicant();
        PersonDTO representative = application.getRepresentative();
        return createDocCorrespondents(representative, applicant, application.getContactAddress(), abdocsSettlementsMap);
    }

    public static List<DocCorrespondent> createDocCorrespondents(PersonDTO representative, PersonDTO applicant, AddressDTO contactAddress, List<ExternalNomenclaturesMapDTO> abdocsSettlementsMap) {
        if (Objects.isNull(applicant)) {
            return null;
        }
        boolean hasRepresentative = Objects.nonNull(representative);

        DocCorrespondent docCorrespondent = new DocCorrespondent();
        docCorrespondent.setCorrespondent(covertToCorrespondent(hasRepresentative ? representative : applicant, contactAddress, abdocsSettlementsMap));
        docCorrespondent.setDocCorrespondentType(hasRepresentative ? DocCorrespondentType.Representative : DocCorrespondentType.Applicant);

        return Collections.singletonList(docCorrespondent);
    }

    public static Correspondent covertToCorrespondent(PersonDTO person, AddressDTO address, List<ExternalNomenclaturesMapDTO> abdocsSettlementsMap) {
        Correspondent correspondent = new Correspondent();
        setCorrespondentType(person, correspondent);
        setCorrespondentName(person, correspondent);
        correspondent.setCorrespondentGroupId(CORRESPONDENTS_OTHERS_GROUP_ID);
        correspondent.setUin(person.getCivilId());
        int personId = person.getId() == null ? -1 : person.getId();
        correspondent.setExternalId(personId);
        CorrespondentContact correspondentContact = covertToCorrespondentContact(address, person, abdocsSettlementsMap);
        correspondent.setCorrespondentContacts(Collections.singletonList(correspondentContact));
        return correspondent;
    }

    private static void setCorrespondentName(PersonDTO person, Correspondent correspondent) {
        if (PersonUtils.isNaturalPerson(person)) {
            correspondent.setFirstName(person.getFirstName());
            correspondent.setMiddleName(person.getMiddleName());
            correspondent.setLastName(person.getLastName());
        }

        if (PersonUtils.isLegalEntity(person)) {
            correspondent.setName(person.getLegalName());
        }
    }

    private static void setCorrespondentType(PersonDTO person, Correspondent correspondent) {
        if (PersonUtils.isNaturalPerson(person)) {
            String citizenshipCountryCode = Objects.isNull(person.getCitizenship()) || Objects.isNull(person.getCitizenship().getId()) ? null : person.getCitizenship().getId();
            if (DefaultValue.BG_COUNTRY_CODE.equals(citizenshipCountryCode)) {
                correspondent.setCorrespondentType(CorrespondentType.BulgarianCitizen);
            } else {
                correspondent.setCorrespondentType(CorrespondentType.Foreigner);
            }
        }

        if (PersonUtils.isLegalEntity(person)) {
            String originCountryCode = Objects.isNull(person.getOriginCountry()) || Objects.isNull(person.getOriginCountry().getId()) ? null : person.getOriginCountry().getId();
            if (DefaultValue.BG_COUNTRY_CODE.equals(originCountryCode)) {
                correspondent.setCorrespondentType(CorrespondentType.LegalEntity);
            } else {
                correspondent.setCorrespondentType(CorrespondentType.ForeignLegalEntity);
            }
        }
    }

    public static CorrespondentContact covertToCorrespondentContact(AddressDTO address, PersonDTO person, List<ExternalNomenclaturesMapDTO> abdocsSettlementsMap) {
        CorrespondentContact contact = new CorrespondentContact();
        contact.setName(PersonUtils.getPersonName(person));
        if (Objects.nonNull(address)) {
            contact.setEmail(address.getEmail());
            contact.setPhone(address.getPhone());
            contact.setAddress(address.getAddress());
            contact.setFax(address.getFax());
            if (address.getSettlement() != null) {
                String settlementId = abdocsSettlementsMap == null ? null : abdocsSettlementsMap.stream().filter(r -> Objects.equals(address.getSettlement().getId(), r.getInternalNomId())).findFirst().map(ExternalNomenclaturesMapDTO::getExternalNomId).orElse(null);
                contact.setSettlementId(IntegerUtils.parseInteger(settlementId, null));
            }
            contact.setPostCode(address.getPostCode());
            if (Objects.nonNull(address.getCountry())) {
                contact.setCountry(new Country(address.getCountry().getId()));
            }
            int addressId = address.getId() == null ? -1 : address.getId();
            contact.setExternalId(addressId);

        } else {
            contact.setEmail(person.getEmail());
            if (Objects.nonNull(person.getCitizenship())) {
                contact.setCountry(new Country(person.getCitizenship().getId()));
            }
        }

        return contact;
    }
}
