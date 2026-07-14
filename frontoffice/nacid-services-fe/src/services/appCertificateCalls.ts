import { axiosClientServices as axiosClient } from "@duosoftbg/nacid-frontoffice-components";

const endpoints = {
  getAppCertificate: "/app-certificate",
};

export const getApplicationCertificate = (applicationId, dossierNumber, accessCode, token) => () => {
  return axiosClient.get(endpoints.getAppCertificate, {
    params: {
      applicationId,
      dossierNumber,
      accessCode,
      captchaToke: token,
    },
  });
};
