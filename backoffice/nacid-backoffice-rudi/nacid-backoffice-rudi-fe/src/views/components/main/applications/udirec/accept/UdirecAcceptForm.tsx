import * as React from "react";
import { AppType } from "@duosoftbg/nacid-backoffice-components";
import { BoxSpg } from "@duosoftbg/nacid-components";
import ApplicantAcceptSection from "../../common/accept/sections/ApplicantAcceptSection";
import RepresentativeAcceptSection from "../../common/accept/sections/RepresentativeAcceptSection";
import ContactAddressAcceptSection from "../../common/accept/sections/ContactAddressAcceptSection";
import SimilarDiplomasSection from "../../common/sections/educationData/similarDiplomas/SimilarDiplomasSection";
import CertificateDocumentReceiveMethodAcceptSections from "../../common/accept/sections/CertificateDocumentReceiveMethodAcceptSections";

const UdirecAcceptForm = () => {
  const appType = AppType.UDIREC_APPLICATION;

  return (
    <BoxSpg>
      <ApplicantAcceptSection appType={appType} />
      <RepresentativeAcceptSection appType={appType} />
      <ContactAddressAcceptSection appType={appType} />
      <CertificateDocumentReceiveMethodAcceptSections appType={appType} />
      {/*<BaseUniversityAcceptSection appType={appType} />*/}
      <SimilarDiplomasSection
        isEfiling={true}
        ownerCivilIdPointer="viewData.applicant.civilId"
        ownerFirstNamePointer="viewData.applicant.firstName"
        ownerMiddleNamePointer="viewData.applicant.middleName"
        ownerLastNamePointer="viewData.applicant.lastName"
        primaryUniversityNamePointer="viewData.baseUniversity.bgName"
        diplomaCountryNamePointer="viewData.baseUniversity.country.name"
        diplomaDatePointer="viewData.diplomaDate"
        specialitiesPointer="viewData.trainingCourseSpecialities"
        eduLevelPointer="viewData.originalEduLevelName"
        eduLevelTranslatedPointer="viewData.originalEduLevelTranslated"
        diplomaOwnerEanPointer="viewData.diplomaOwnerEan"
        birthDatePointer="viewData.applicant.birthDate"
        birthCountryPointer="viewData.applicant.originCountry.name"
      />
    </BoxSpg>
  );
};

export default UdirecAcceptForm;
