import * as React from "react";
import { AppType } from "@duosoftbg/nacid-backoffice-components";
import UdirecSummary from "../../udirec/summary/UdirecSummary";
import DocrecSummary from "../../docrec/summary/DocrecSummary";
import SarSummary from "../../sar/summary/SarSummary";

type ApplicationSummaryProps = {
  appType: AppType;
  applicationId: number | string;
};

const ApplicationSummary = ({ appType, applicationId }: ApplicationSummaryProps) => {
  switch (appType) {
    case AppType.UDIREC_APPLICATION: {
      return <UdirecSummary id={applicationId} />;
    }
    case AppType.DOCREC_APPLICATION: {
      return <DocrecSummary id={applicationId} />;
    }
    case AppType.SAR_APPLICATION: {
      return <SarSummary id={applicationId} />;
    }
  }
};

export default ApplicationSummary;
