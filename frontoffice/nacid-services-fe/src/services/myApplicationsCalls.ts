import { axiosClientServices as axiosClient } from "@duosoftbg/nacid-frontoffice-components";

const endpoints = {
  filter: "/my-applications/filter",
  statuses: "/my-applications/statuses",
};

export const filterMyApplications = (filter) => () => {
  return axiosClient.post(`${endpoints.filter}`, filter);
};

export const getMyApplicationsStatuses = () => () => {
  return axiosClient.get(`${endpoints.statuses}`);
};
