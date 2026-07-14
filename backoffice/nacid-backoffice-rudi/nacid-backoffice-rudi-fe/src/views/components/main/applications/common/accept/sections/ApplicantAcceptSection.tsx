import { ApplicantView } from "../viewData/viewData";
import {
  ApiUrlBuilder,
  ApplicantSection,
  AppType,
  PersonRole,
  AcceptFormPrefilledModalUtils,
} from "@duosoftbg/nacid-backoffice-components";
import * as React from "react";
import useFormViewData from "../viewData/useFormViewData";
import { useParams } from "react-router-dom";

type ApplicantAcceptSectionProps = {
  appType: AppType;
};

const ApplicantAcceptSection = ({ appType }: ApplicantAcceptSectionProps) => {
  const { id } = useParams();
  const { viewData } = useFormViewData();

  return (
    <ApplicantSection
      appType={appType}
      pointer={"applicantId"}
      viewDataComponent={<ApplicantView />}
      searchFormDefaultValues={AcceptFormPrefilledModalUtils.fillPersonData(viewData?.applicant)}
      loadPersonData={{
        title: "l.loadFromFoApplication",
        url: ApiUrlBuilder.importFoPersonUrl(id, PersonRole.APPLICANT, appType),
      }}
    />
  );
};

export default ApplicantAcceptSection;
