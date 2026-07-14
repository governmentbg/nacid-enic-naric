package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.common.main;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicantDiplomaNamesDTO;

public interface RegprofMandatoryMainData {
    public Integer getApplicantId();

    public void setApplicantId(Integer applicantId);

    String getPersonalDocumentTypeId();

    public void setPersonalDocumentTypeId(String personalDocumentTypeId);

    Integer getRepresentativeId();

    void setRepresentativeId(Integer representativeId);

    Integer getRepresentativeCompanyId();

    void setRepresentativeCompanyId(Integer representativeCompanyId);

    Boolean getRepresentativeCompanyFlag();

    void setRepresentativeCompanyFlag(Boolean representativeCompanyFlag);

    String getRepresentativeCapacity();

    void setRepresentativeCapacity(String representativeCapacity);

    Boolean getDiffDiplomaNamesFlag();

    void setDiffDiplomaNamesFlag(Boolean diffDiplomaNamesFlag);

    ApplicantDiplomaNamesDTO getApplicantDiplomaNames();

    void setApplicantDiplomaNames(ApplicantDiplomaNamesDTO applicantDiplomaNames);

    Integer getContactAddressId();

    void setContactAddressId(Integer contactAddressId);

    String getServiceTypeId();

    void setServiceTypeId(String serviceTypeId);

    Boolean getApostilleApplicationFlag();

    void setApostilleApplicationFlag(Boolean apostilleApplicationFlag);


}
