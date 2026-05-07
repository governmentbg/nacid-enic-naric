package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.main.mandatory;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;

public interface LibservMandatoryMainData {
    Integer getApplicantId();
    void setApplicantId(Integer applicantId);
    Integer getRepresentativeId();

    Integer getRepresentativeCompanyId();

    Boolean getRepresentativeCompanyFlag();

    String getRepresentativeCapacity();

    void setRepresentativeId(Integer representativeId);

    void setRepresentativeCompanyId(Integer representativeCompanyId);

    void setRepresentativeCompanyFlag(Boolean representativeCompanyFlag);


    void setRepresentativeCapacity(String representativeCapacity);

    Integer getContactAddressId();

    void setContactAddressId(Integer contactAddressId);

    DocumentReceiveMethodFormDTO getDocumentReceiveMethod() ;

     void setDocumentReceiveMethod(DocumentReceiveMethodFormDTO documentReceiveMethod);

}
