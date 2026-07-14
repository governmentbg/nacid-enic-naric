import { axiosClientServices as axiosClient } from "@duosoftbg/nacid-frontoffice-components";

const endpoints = {
  filter: "/my-correspondence/filter",
  read: "/my-correspondence/read",
  getForApplication: "/my-correspondence/for-application",
  details: "/my-correspondence/details",
};

export const filterMyCorrespondence = (filter) => () => {
  return axiosClient.post(`${endpoints.filter}`, filter);
};

export const readMyCorrespondence = (applicationId, id) => () => {
  return axiosClient.post(`${endpoints.read}/${applicationId}/${id}`);
};

export const getApplicationCorrespondence = (applicationId) => () => {
  return axiosClient.get(`${endpoints.getForApplication}/${applicationId}`);
};

export const getCorrespondenceDetails = (applicationId, id) => () => {
  return axiosClient.get(`${endpoints.details}/${applicationId}/${id}`);
};
