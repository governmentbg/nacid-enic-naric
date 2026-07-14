package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.main.mandatory;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicantDiplomaNamesDTO;

public interface RudiMandatoryMainData {

    Integer getApplicantId();

    Integer getRepresentativeId();

    Integer getRepresentativeCompanyId();

    Boolean getRepresentativeCompanyFlag();

    Boolean getRepresentativeAuthorizedFlag();

    String getRepresentativeCapacity();

    Boolean getIsBgAddressPartOfRepresentative();

    Boolean getDiffDiplomaNamesFlag();

    ApplicantDiplomaNamesDTO getApplicantDiplomaNames();

    Integer getContactAddressId();

    Boolean getOfficialEmailCommunicationFlag();

    String getServiceTypeId();

    void setApplicantId(Integer applicantId);

    void setRepresentativeId(Integer representativeId);

    void setRepresentativeCompanyId(Integer representativeCompanyId);

    void setRepresentativeCompanyFlag(Boolean representativeCompanyFlag);

    void setRepresentativeAuthorizedFlag(Boolean representativeAuthorizedFlag);

    void setRepresentativeCapacity(String representativeCapacity);

    void setIsBgAddressPartOfRepresentative(Boolean isBgAddressPartOfRepresentative);

    void setDiffDiplomaNamesFlag(Boolean diffDiplomaNamesFlag);

    void setApplicantDiplomaNames(ApplicantDiplomaNamesDTO applicantDiplomaNames);

    void setContactAddressId(Integer contactAddressId);

    void setOfficialEmailCommunicationFlag(Boolean officialEmailCommunicationFlag);

    void setServiceTypeId(String serviceTypeId);
}
