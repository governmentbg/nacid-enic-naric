import React from "react";
import { PageContentBox } from "@duosoftbg/nacid-components";
import InfoText from "./InfoText";
import InfoActions from "./InfoActions";

const ServiceInfo = ({ baseUrl, descriptionCode }: { baseUrl: string; descriptionCode: string }) => {
  return (
    <React.Fragment>
      <PageContentBox>
        <InfoText descriptionCode={descriptionCode} />
      </PageContentBox>
      <InfoActions baseUrl={baseUrl} />
    </React.Fragment>
  );
};
export default ServiceInfo;
