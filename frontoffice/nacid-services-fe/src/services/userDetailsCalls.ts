import { axiosClientCore } from "@duosoftbg/nacid-frontoffice-components";

const endpoints = {
  getUserDetails: "/nacid-user-details",
};

export const getLoggedUserDetails = () => () => {
  return axiosClientCore.get(`${endpoints.getUserDetails}`, {});
};
