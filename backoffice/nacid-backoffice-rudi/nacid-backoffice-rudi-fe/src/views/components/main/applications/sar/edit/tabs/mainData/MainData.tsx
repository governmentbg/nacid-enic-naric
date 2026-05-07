import React from "react";
import { BoxSpg } from "@duosoftbg/nacid-components";
import {
  AddressDialogsProvider,
  AdditionalDataSection,
  ApplicantDiplomaNamesSection,
  ApplicantSection,
  AppSectionTitle,
  AppType,
  ContactAddressSection,
  DocumentReceiveMethodSection,
  PersonDialogsProvider,
  RepresentativeSection,
  ServiceTypeSection,
  NotesSection,
  ResponsibleUserSection,
  ResponsibleUserHistorySection,
  CertificateDocumentReceiveMethodSections,
} from "@duosoftbg/nacid-backoffice-components";
import DiplomaOwnerSection from "./sections/diplomaOwner/DiplomaOwnerSection";
import MainDataFormInitializer from "../../../../common/sections/mainData/MainDataFormInitializer";
import InOutNumbersSection from "./sections/ioNumber/InOutNumbersSection";
import { useParams } from "react-router-dom";

const MainData = () => {
  const { id } = useParams();
  const appType = AppType.SAR_APPLICATION;

  return (
    <BoxSpg>
      <AppSectionTitle title={"t.appSections.dataFromApplication"} />
      <BoxSpg>
        <MainDataFormInitializer appType={appType}>
          <ApplicantSection appType={appType} pointer={"applicantId"} />
          <RepresentativeSection appType={appType} pointer={"representativeId"} />
          <DiplomaOwnerSection appType={appType} pointer={"diplomaOwnerId"} withEan />
          <ApplicantDiplomaNamesSection diffDiplomaNamesFlagTitle={"l.diffDiplomaNamesFlag"} />
          <ContactAddressSection appType={appType} pointer={"contactAddressId"} />
          <CertificateDocumentReceiveMethodSections appType={appType} />
          <InOutNumbersSection />
          <ServiceTypeSection appType={appType} />
          <AdditionalDataSection appType={appType} />
          <ResponsibleUserSection />
          <ResponsibleUserHistorySection id={id} />
          <NotesSection />
        </MainDataFormInitializer>
      </BoxSpg>

      <PersonDialogsProvider />
      <AddressDialogsProvider />
    </BoxSpg>
  );
};

export default MainData;
