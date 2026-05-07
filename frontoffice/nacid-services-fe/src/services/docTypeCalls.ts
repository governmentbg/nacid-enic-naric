import { axiosClientServices as axiosClient } from "@duosoftbg/nacid-frontoffice-components";

const endpoints = {
  getAppDocTypes: "/doc-type/app-doc-types",
};

export const getAppDocTypes = (id) => () => {
  return axiosClient.get(`${endpoints.getAppDocTypes}`, {
    params: {
      id,
    },
  });
};
