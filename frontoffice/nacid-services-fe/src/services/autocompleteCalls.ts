import { axiosClientServices as axiosClient } from "@duosoftbg/nacid-frontoffice-components";

const endpoints = {
  universities: "/autocomplete/universities",
  universityFaculties: "/autocomplete/university-faculties",
  specialities: "/autocomplete/specialities",
  originalSpecialities: "/autocomplete/original-specialities",
  qualifications: "/autocomplete/qualifications",
  originalQualifications: "/autocomplete/original-qualifications",
  higherSpecialities: "/autocomplete/higher-specialities",
  higherQualifications: "/autocomplete/higher-qualifications",
  sdkSpecialities: "/autocomplete/sdk-specialities",
  sdkQualifications: "/autocomplete/sdk-qualifications",
  secondarySpecialities: "/autocomplete/secondary-specialities",
  secondaryQualifications: "/autocomplete/secondary-qualifications",
  profInstitutions: "/autocomplete/prof-institutions",
  profInstitutionFormerNames: "/autocomplete/prof-institution-former-names",
  certificateProfQualification: "/autocomplete/certificate-prof-qualifications",
  originalEduLevels: "/autocomplete/original-edu-levels",
  originalEduLevelsTranslated: "/autocomplete/original-edu-levels-translated",
  professionNames: "/autocomplete/profession-names",
};

export const getUniversitiesAutocomplete = (name, page, pageSize) => async () => {
  const response = await axiosClient.get(`${endpoints.universities}`, {
    params: {
      name,
      page,
      pageSize,
    },
  });
  return response.data;
};

export const getUniversityFacultyAutocomplete = (name, page, pageSize, additionalParams) => async () => {
  const response = await axiosClient.get(`${endpoints.universityFaculties}`, {
    params: {
      name,
      page,
      pageSize,
      ...additionalParams,
    },
  });
  return response.data;
};

export const getSpecialitiesAutocomplete = (name, page, pageSize) => async () => {
  const response = await axiosClient.get(`${endpoints.specialities}`, {
    params: {
      name,
      page,
      pageSize,
    },
  });
  return response.data;
};

export const getOriginalSpecialitiesAutocomplete = (name, page, pageSize) => async () => {
  const response = await axiosClient.get(`${endpoints.originalSpecialities}`, {
    params: {
      name,
      page,
      pageSize,
    },
  });
  return response.data;
};

export const getQualificationsAutocomplete = (name, page, pageSize) => async () => {
  const response = await axiosClient.get(`${endpoints.qualifications}`, {
    params: {
      name,
      page,
      pageSize,
    },
  });
  return response.data;
};

export const getOriginalQualificationsAutocomplete = (name, page, pageSize) => async () => {
  const response = await axiosClient.get(`${endpoints.originalQualifications}`, {
    params: {
      name,
      page,
      pageSize,
    },
  });
  return response.data;
};

export const getHigherSpecialitiesAutocomplete = (name, page, pageSize) => async () => {
  const response = await axiosClient.get(`${endpoints.higherSpecialities}`, {
    params: {
      name,
      page,
      pageSize,
    },
  });
  return response.data;
};

export const getHigherQualificationsAutocomplete = (name, page, pageSize) => async () => {
  const response = await axiosClient.get(`${endpoints.higherQualifications}`, {
    params: {
      name,
      page,
      pageSize,
    },
  });
  return response.data;
};

export const getSdkSpecialitiesAutocomplete = (name, page, pageSize) => async () => {
  const response = await axiosClient.get(`${endpoints.sdkSpecialities}`, {
    params: {
      name,
      page,
      pageSize,
    },
  });
  return response.data;
};

export const getSdkQualificationsAutocomplete = (name, page, pageSize) => async () => {
  const response = await axiosClient.get(`${endpoints.sdkQualifications}`, {
    params: {
      name,
      page,
      pageSize,
    },
  });
  return response.data;
};

export const getSecondarySpecialitiesAutocomplete = (name, page, pageSize, additionalParams) => async () => {
  const response = await axiosClient.get(`${endpoints.secondarySpecialities}`, {
    params: {
      name,
      page,
      pageSize,
      ...additionalParams,
    },
  });
  return response.data;
};

export const getSecondaryQualificationsAutocomplete = (name, page, pageSize) => async () => {
  const response = await axiosClient.get(`${endpoints.secondaryQualifications}`, {
    params: {
      name,
      page,
      pageSize,
    },
  });
  return response.data;
};

export const getProfInstitutionsAutocomplete = (name, page, pageSize, additionalParams) => async () => {
  const response = await axiosClient.get(`${endpoints.profInstitutions}`, {
    params: {
      name,
      page,
      pageSize,
      ...additionalParams,
    },
  });
  return response.data;
};

export const getProfInstitutionFormerNamesAutocomplete = (name, page, pageSize, additionalParams) => async () => {
  const response = await axiosClient.get(`${endpoints.profInstitutionFormerNames}`, {
    params: {
      name,
      page,
      pageSize,
      ...additionalParams,
    },
  });
  return response.data;
};

export const getCertificateProfQualificationsAutocomplete = (name, page, pageSize) => async () => {
  const response = await axiosClient.get(`${endpoints.certificateProfQualification}`, {
    params: {
      name,
      page,
      pageSize,
    },
  });
  return response.data;
};

export const getOriginalEduLevelsAutocomplete = (name, page, pageSize) => async () => {
  const response = await axiosClient.get(`${endpoints.originalEduLevels}`, {
    params: {
      name,
      page,
      pageSize,
    },
  });
  return response.data;
};

export const getOriginalEduLevelsTranslatedAutocomplete = (name, page, pageSize) => async () => {
  const response = await axiosClient.get(`${endpoints.originalEduLevelsTranslated}`, {
    params: {
      name,
      page,
      pageSize,
    },
  });
  return response.data;
};

export const getProfessionNamesAutocomplete = (name, page, pageSize) => async () => {
  const response = await axiosClient.get(`${endpoints.professionNames}`, {
    params: {
      name,
      page,
      pageSize,
    },
  });
  return response.data;
};
