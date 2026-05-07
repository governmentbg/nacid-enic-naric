import { UniversityView } from "../viewData/viewData";
import { AcceptFormPrefilledModalUtils, AppType } from "@duosoftbg/nacid-backoffice-components";
import * as React from "react";
import useFormViewData from "../viewData/useFormViewData";
import UniversitySection from "../../sections/educationData/university/UniversitySection";

type BaseUniversityAcceptSectionProps = {
  appType: AppType;
};

const BaseUniversityAcceptSection = ({ appType }: BaseUniversityAcceptSectionProps) => {
  const { viewData } = useFormViewData();

  return (
    <UniversitySection
      appType={appType}
      baseUniversityIdPointer={"baseUniversityId"}
      viewDataComponent={<UniversityView />}
      searchFormDefaultValues={AcceptFormPrefilledModalUtils.fillUniversityData(viewData?.baseUniversity)}
      showSecondaryUniversities={false}
      showTranslationFields={false}
      showContactFields={false}
    />
  );
};

export default BaseUniversityAcceptSection;
