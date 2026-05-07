import * as React from "react";
import { CardContent } from "@mui/material";
import { BoxSpg, CardSpg, FormSection } from "@duosoftbg/nacid-components";
import {
  AdditionalDataSection,
  AddressDialogsProvider,
  ApplicantDiplomaNamesSection,
  ApplicantSection,
  AppSectionTitle,
  AppType,
  CertificateDocumentReceiveMethodSections,
  ContactAddressSection,
  PersonDialogsProvider,
  RepresentativeSection,
  ServiceTypeSection,
} from "@duosoftbg/nacid-backoffice-components";
import TrainingCourseSpecialities from "../../common/components/TrainingCourseSpecialities";
import UniversitySection from "../sections/educationData/university/UniversitySection";
import BaseUniversityDialogsProvider from "../../common/sections/educationData/university/components/dialog/BaseUniversityDialogsProvider";
import AppReceptionFormInitializer from "./AppReceptionFormInitializer";
import SarFlagSection from "../../sar/create/components/SarFlagSection";
import DiplomaOwnerSection from "../../sar/edit/tabs/mainData/sections/diplomaOwner/DiplomaOwnerSection";
import { TitleUtils } from "../../../../../../utils/helpers";

type AppReceptionProps = {
  appType: AppType;
};

const AppReception = ({ appType }: AppReceptionProps) => {
  console.log("appType", appType);
  return (
    <CardSpg my={4} style={{ overflow: "visible" }}>
      <CardContent style={{ position: "relative" }}>
        <AppSectionTitle title={TitleUtils.selectTitleByAppType(appType)} my={0} />
        <BoxSpg>
          <AppReceptionFormInitializer appType={appType}>
            <SarFlagRenderer appType={appType} />
            <ApplicantSection appType={appType} pointer={"applicantId"} />
            <RepresentativeSection appType={appType} pointer={"representativeId"} />
            <DiplomaOwnerRenderer appType={appType} />
            <ApplicantDiplomaNamesSection diffDiplomaNamesFlagTitle={"l.diffDiplomaNamesFlag"} />
            <ContactAddressSection appType={appType} pointer={"contactAddressId"} />
            <CertificateDocumentReceiveMethodSections appType={appType} />
            <AdditionalDataSection appType={appType} />
            <UniversitySection
              appType={appType}
              baseUniversityIdPointer={"baseUniversityId"}
              showTranslationFields={false}
              showContactFields={false}
              showSecondaryUniversities={false}
              showManualTempUniName={true}
            />
            <TrainingCourseSpecialitiesRenderer appType={appType} />
            <ServiceTypeSection appType={appType} selectFirstOptionByDefault />
          </AppReceptionFormInitializer>
        </BoxSpg>

        <PersonDialogsProvider />
        <AddressDialogsProvider />
        <BaseUniversityDialogsProvider />
      </CardContent>
    </CardSpg>
  );
};

const SarFlagRenderer = ({ appType }: { appType: AppType }) => {
  if (appType === AppType.SAR_APPLICATION) {
    return <SarFlagSection />;
  }

  return null;
};

const DiplomaOwnerRenderer = ({ appType }: { appType: AppType }) => {
  if (appType === AppType.SAR_APPLICATION) {
    return <DiplomaOwnerSection appType={appType} pointer={"diplomaOwnerId"} />;
  }

  return null;
};

const TrainingCourseSpecialitiesRenderer = ({ appType }: { appType: AppType }) => {
  if (appType === AppType.SAR_APPLICATION || appType === AppType.UDIREC_APPLICATION) {
    return (
      <FormSection label={"l.specialities"}>
        <BoxSpg marginTop={2}>
          <TrainingCourseSpecialities wrapper={"form-section"} />
        </BoxSpg>
      </FormSection>
    );
  }

  return null;
};

export default AppReception;
