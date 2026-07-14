import { RepresentativeView } from "../viewData/viewData";
import {
  ApiUrlBuilder,
  AppType,
  PersonRole,
  RepresentativeSection,
  AcceptFormPrefilledModalUtils,
} from "@duosoftbg/nacid-backoffice-components";
import * as React from "react";
import useFormViewData from "../viewData/useFormViewData";
import { useParams } from "react-router-dom";

type RepresentativeAcceptSectionProps = {
  appType: AppType;
};

const RepresentativeAcceptSection = ({ appType }: RepresentativeAcceptSectionProps) => {
  const { id } = useParams();
  const { viewData } = useFormViewData();

  return (
    <RepresentativeSection
      appType={appType}
      pointer={"representativeId"}
      viewDataComponent={<RepresentativeView />}
      searchFormDefaultValues={AcceptFormPrefilledModalUtils.fillPersonData(viewData?.representative)}
      searchFormDefaultValuesCompany={AcceptFormPrefilledModalUtils.fillPersonData(viewData?.representativeCompany)}
      loadPersonData={{
        title: "l.loadFromFoApplication",
        url: ApiUrlBuilder.importFoPersonUrl(id, PersonRole.REPRESENTATIVE, appType),
      }}
      loadPersonDataCompany={{
        title: "l.loadFromFoApplication",
        url: ApiUrlBuilder.importFoPersonUrl(id, PersonRole.REPRESENTATIVE_COMPANY, appType),
      }}
    />
  );
};

export default RepresentativeAcceptSection;
