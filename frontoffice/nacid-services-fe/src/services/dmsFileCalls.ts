import { axiosClientServices as axiosClient, ProcessEnvironments } from "@duosoftbg/nacid-frontoffice-components";

const endpoints = {
  download: "/bo-dms-file",
};

export const downloadDmsFileForDocAndId = (docId, fileId, token) => () => {
  return axiosClient.get(`${ProcessEnvironments.Api.FrontOffice.Services}/api/v1${endpoints.download}`, {
    params: {
      docId,
      fileId,
      captchaToken: token,
    },
    responseType: "blob",
  });
};
