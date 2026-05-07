import { DiplomaOwnerView } from "../viewData/viewData";
import {
  ApiUrlBuilder,
  AppType,
  PersonRole,
  AcceptFormPrefilledModalUtils,
} from "@duosoftbg/nacid-backoffice-components";
import * as React from "react";
import useFormViewData from "../viewData/useFormViewData";
import DiplomaOwnerSection from "../../../sar/edit/tabs/mainData/sections/diplomaOwner/DiplomaOwnerSection";
import { useParams } from "react-router-dom";

type DiplomaOwnerAcceptSectionProps = {
  appType: AppType;
};

const DiplomaOwnerAcceptSection = ({ appType }: DiplomaOwnerAcceptSectionProps) => {
  const { id } = useParams();
  const { viewData } = useFormViewData();

  return (
    <DiplomaOwnerSection
      appType={appType}
      pointer={"diplomaOwnerId"}
      viewDataComponent={<DiplomaOwnerView />}
      searchFormDefaultValues={AcceptFormPrefilledModalUtils.fillPersonData(viewData?.diplomaOwner)}
      loadPersonData={{
        title: "l.loadFromFoApplication",
        url: ApiUrlBuilder.importFoPersonUrl(id, PersonRole.DIPLOMA_OWNER, appType),
      }}
    />
  );
};

export default DiplomaOwnerAcceptSection;
