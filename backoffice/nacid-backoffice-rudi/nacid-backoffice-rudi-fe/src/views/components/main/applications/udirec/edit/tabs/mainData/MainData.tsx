import React from "react";
import { BoxSpg } from "@duosoftbg/nacid-components";
import {
  AdditionalDataSection,
  AddressDialogsProvider,
  ApplicantDiplomaNamesSection,
  ApplicantSection,
  AppSectionTitle,
  AppType,
  CertificateDocumentReceiveMethodSections,
  ContactAddressSection,
  NotesSection,
  PersonDialogsProvider,
  RepresentativeSection,
  ResponsibleUserHistorySection,
  ResponsibleUserSection,
  ServiceTypeSection,
} from "@duosoftbg/nacid-backoffice-components";
import MainDataFormInitializer from "../../../../common/sections/mainData/MainDataFormInitializer";
import { useParams } from "react-router-dom";

const MainData = () => {
  const { id } = useParams();
  const appType = AppType.UDIREC_APPLICATION;

  return (
    <BoxSpg>
      <AppSectionTitle title={"t.appSections.dataFromApplication"} />
      <BoxSpg>
        <MainDataFormInitializer appType={appType}>
          <ApplicantSection appType={appType} pointer={"applicantId"} />
          <RepresentativeSection appType={appType} pointer={"representativeId"} />
          <ApplicantDiplomaNamesSection diffDiplomaNamesFlagTitle={"l.diffDiplomaNamesFlag"} />
          <ContactAddressSection appType={appType} pointer={"contactAddressId"} />
          <CertificateDocumentReceiveMethodSections appType={appType} />
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
