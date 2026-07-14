import { AppType } from "@duosoftbg/nacid-backoffice-components";

const urlsConfig = {
  [AppType.SAR_APPLICATION]: {
    view: `/sar-applications/view/{applicationId}`,
    edit: `/sar-applications/edit/{applicationId}`,
  },
  [AppType.DOCREC_APPLICATION]: {
    view: `/docrec-applications/view/{applicationId}`,
    edit: `/docrec-applications/edit/{applicationId}`,
  },
  [AppType.UDIREC_APPLICATION]: {
    view: `/udirec-applications/view/{applicationId}`,
    edit: `/udirec-applications/edit/{applicationId}`,
  },
};

export const generateUrl = (appType, applicationId, accessType) => {
  return urlsConfig[appType][accessType].replace("{applicationId}", applicationId);
};
