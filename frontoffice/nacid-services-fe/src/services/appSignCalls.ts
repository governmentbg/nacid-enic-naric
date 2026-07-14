import { ProcessEnvironments, axiosClientServices as axiosClient } from "@duosoftbg/nacid-frontoffice-components";

const endpoints = {
  fetchSignDetails: "/app-sign/fetch-details-to-sign",
  uploadSignedFile: "/app-sign/upload",
};

export const fetchAppSignDetails = (id) => () => {
  return axiosClient.get(
    `${ProcessEnvironments.Api.FrontOffice.Services}/api/v1${endpoints.fetchSignDetails}?id=${id}`
  );
};

export const uploadSignedFile = (file) => () => {
  const formData = new FormData();
  formData.append("file", file, file.name);
  formData.append("relativePath", ProcessEnvironments.FilePath.Services);
  const config = {
    headers: { "content-type": "multipart/form-data" },
  };
  return axiosClient.post(endpoints.uploadSignedFile, formData, config);
};
