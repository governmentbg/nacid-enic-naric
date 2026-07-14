import { axiosClientServices as axiosClient } from "@duosoftbg/nacid-frontoffice-components";
import { AppCheckup } from "../types/appCheckupTypes";

const endpoints = {
  subtype: "app-checkup/subtype",
};

export const getAppSubtypeForCheckup = (appCheckup: AppCheckup) => () => {
  return axiosClient.get(`${endpoints.subtype}`, {
    params: {
      dossierNumber: appCheckup.dossierNumber,
      accessCode: appCheckup.accessCode,
      captchaToken: appCheckup.captchaToken,
    },
  });
};

export const buildAppCheckupUrl = (appCheckup: AppCheckup, baseUrl: string) => {
  return `/app-checkup${baseUrl}?dossierNumber=${appCheckup.dossierNumber}&accessCode=${appCheckup.accessCode}&captchaToken=${appCheckup.captchaToken}`;
};
