package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.main;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicantDiplomaNamesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.BgAddressOwner;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.main.mandatory.RudiMandatoryMainData;
import bg.duosoft.nacid.backoffice.core.data.util.common.ReferenceDataUtils;
import org.mapstruct.MappingTarget;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class MainDataMapperUtils {

    public static void afterOverrideMandatoryMainData(RudiMandatoryMainData source, @MappingTarget RudiApplicationDTO target) {
        Boolean isBgAddressPartOfRepresentative = source.getIsBgAddressPartOfRepresentative();
        target.setBgAddressOwner(Objects.nonNull(isBgAddressPartOfRepresentative) && isBgAddressPartOfRepresentative ? BgAddressOwner.REPRESENTATIVE.code() : BgAddressOwner.APPLICANT.code());

        ApplicationDTO application = target.getApplication();
        if (Objects.nonNull(application)) {
            ReferenceDataUtils.setDefaultDomain(application.getServiceType(), ReferenceDataDomain.SERVICE_TYPE);
            ReferenceDataUtils.setDefaultDomain(application.getPersonalDocumentType(), ReferenceDataDomain.PERSONAL_DOCUMENT_TYPE);

            if (Objects.isNull(source.getApplicantId())) {
                application.setApplicant(null);
            }

            if (Objects.isNull(source.getRepresentativeId())) {
                target.setBgAddressOwner(BgAddressOwner.APPLICANT.code());
                target.setRepresentativeAuthorizedFlag(false);

                application.setRepresentative(null);
                application.setRepresentativeCapacity(null);
                application.setRepresentativeCompany(null);
            }

            Boolean representativeCompanyFlag = source.getRepresentativeCompanyFlag();
            if (Objects.isNull(representativeCompanyFlag) || !representativeCompanyFlag || Objects.isNull(source.getRepresentativeCompanyId())) {
                application.setRepresentativeCompany(null);
            }

            if (Objects.isNull(source.getContactAddressId())) {
                application.setContactAddress(null);
                application.setOfficialEmailCommunicationFlag(false);
            }

            Boolean diffDiplomaNamesFlag = source.getDiffDiplomaNamesFlag();
            if (Objects.isNull(diffDiplomaNamesFlag) || !diffDiplomaNamesFlag || Objects.isNull(source.getApplicantDiplomaNames())) {
                application.setApplicantDiplomaNames(null);
            }

            ApplicantDiplomaNamesDTO applicantDiplomaNames = application.getApplicantDiplomaNames();
            if (Objects.nonNull(applicantDiplomaNames)) {
                String civilId = applicantDiplomaNames.getCivilId();
                if (!StringUtils.hasText(civilId)) {
                    applicantDiplomaNames.setCivilIdType(null);
                    applicantDiplomaNames.setForeignIdentifierCountry(null);
                    applicantDiplomaNames.setForeignIdentifierType(null);
                }
            }

            String serviceTypeId = source.getServiceTypeId();
            if (!StringUtils.hasText(serviceTypeId)) {
                application.setServiceType(null);
            }

        }


    }

    public static void afterToMandatoryMainDataDto(RudiApplicationDTO source, @MappingTarget RudiMandatoryMainData target) {
        String bgAddressOwner = source.getBgAddressOwner();
        if (StringUtils.hasText(bgAddressOwner)) {
            target.setIsBgAddressPartOfRepresentative(bgAddressOwner.equalsIgnoreCase(BgAddressOwner.REPRESENTATIVE.code()));
        }

        target.setRepresentativeCompanyFlag(Objects.nonNull(source.getApplication().getRepresentativeCompany()));
    }

}
