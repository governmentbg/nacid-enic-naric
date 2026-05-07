import * as React from "react";
import { BoxSpg } from "@duosoftbg/nacid-components";
import { AppType } from "@duosoftbg/nacid-backoffice-components";
import ApplicantAcceptSection from "../../common/accept/sections/ApplicantAcceptSection";
import RepresentativeAcceptSection from "../../common/accept/sections/RepresentativeAcceptSection";
import DiplomaOwnerAcceptSection from "../../common/accept/sections/DiplomaOwnerAcceptSection";
import ContactAddressAcceptSection from "../../common/accept/sections/ContactAddressAcceptSection";
import SimilarDiplomasSection from "../../common/sections/educationData/similarDiplomas/SimilarDiplomasSection";
import CertificateDocumentReceiveMethodAcceptSections from "../../common/accept/sections/CertificateDocumentReceiveMethodAcceptSections";

const SarAcceptForm = () => {
  const appType = AppType.SAR_APPLICATION;

  return (
    <BoxSpg>
      <ApplicantAcceptSection appType={appType} />
      <RepresentativeAcceptSection appType={appType} />
      <DiplomaOwnerAcceptSection appType={appType} />
      <ContactAddressAcceptSection appType={appType} />
      <CertificateDocumentReceiveMethodAcceptSections appType={appType} />
      {/*<BaseUniversityAcceptSection appType={appType} />*/}
      <SimilarDiplomasSection
        isEfiling={true}
        ownerCivilIdPointer="viewData.diplomaOwner.civilId"
        ownerFirstNamePointer="viewData.diplomaOwner.firstName"
        ownerMiddleNamePointer="viewData.diplomaOwner.middleName"
        ownerLastNamePointer="viewData.diplomaOwner.lastName"
        primaryUniversityNamePointer="viewData.baseUniversity.bgName"
        diplomaCountryNamePointer="viewData.baseUniversity.country.name"
        diplomaDatePointer="viewData.diplomaDate"
        specialitiesPointer="viewData.trainingCourseSpecialities"
        eduLevelPointer="viewData.originalEduLevelName"
        eduLevelTranslatedPointer="viewData.originalEduLevelTranslated"
        diplomaOwnerEanPointer="viewData.diplomaOwnerEan"
        birthDatePointer="viewData.diplomaOwner.birthDate"
        birthCountryPointer="viewData.diplomaOwner.originCountry.name"
      />
    </BoxSpg>
  );
};

export default SarAcceptForm;
