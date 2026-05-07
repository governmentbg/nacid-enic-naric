package bg.duosoft.nacid.backoffice.core.data.util.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.LegalType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.PersonRole;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonUtils {

    public static boolean isNaturalPerson(PersonDTO person) {
        if (Objects.isNull(person) || ReferenceDataUtils.isEmptyRefDataId(person.getLegalType())) {
            return false;
        }

        String legalType = person.getLegalType().getId();
        return LegalType.NATURAL_PERSON.code().equalsIgnoreCase(legalType);
    }

    public static boolean isLegalEntity(PersonDTO person) {
        if (Objects.isNull(person) || ReferenceDataUtils.isEmptyRefDataId(person.getLegalType())) {
            return false;
        }

        String legalType = person.getLegalType().getId();
        return LegalType.LEGAL_ENTITY.code().equalsIgnoreCase(legalType);
    }


    public static String getPersonName(PersonDTO person) {
        if (Objects.isNull(person)) {
            return null;
        }

        if (isNaturalPerson(person)) {
            return Stream.of(person.getFirstName(), person.getMiddleName(), person.getLastName())
                    .filter(StringUtils::hasText)
                    .map(String::trim)
                    .collect(Collectors.joining(" "));
        }

        return person.getLegalName();
    }

    public static PersonDTO extractPersonByRole(PersonRole personRole, ApplicationDTO application) {
        switch (personRole) {
            case APPLICANT -> {
                return application.getApplicant();
            }
            case REPRESENTATIVE -> {
                return application.getRepresentative();
            }
            case REPRESENTATIVE_COMPANY -> {
                return application.getRepresentativeCompany();
            }
            default -> {
                return null;
            }
        }
    }

}
