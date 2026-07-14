import qs from "qs";
import { ProcessEnvironments, axiosClientCore } from "@duosoftbg/nacid-frontoffice-components";

const endpoints = {
  contentManagement: "/content-management",
  documentReceiveMethod: "/document-receive-methods",
  countries: "/countries",
  settlement: {
    autocomplete: "/settlements/autocomplete",
    baseUrl: "/settlements",
  },
  person: {
    extractDateFromCivilId: "/birth-date/extraction",
    baseUrl: "/person",
  },
  referenceData: `/reference-data`,
  profExperienceDocType: `/prof-experience-doc-type`,
  language: `/language`,
  graduationDocType: "/graduation-doc-type",
  profGroup: "/prof-group",
  docType: "/doc-type",
  cfgEduLevel: "/cfg-edu-level",
  cfgRecognitionCategory: "/cfg-recognition-category",
  cfgGraduationWay: "/cfg-graduation-way",
  cfgServiceType: "/cfg-service-type",
  file: {
    upload: "/file-store/upload",
    get: "/file-store/file-content",
  },
  nationalUniversities: "/national-universities",
};

const commonCoreApiFilterFunction = async (url, params) => {
  const response = await axiosClientCore.get(url, {
    params,
    paramsSerializer: (params) => {
      return qs.stringify(params);
    },
  });

  return response.data;
};

export const getContentManagementDataById = (id: string) => () => {
  return axiosClientCore.get(`${endpoints.contentManagement}/${id}`);
};

export const getDocumentReceiveMethodOptions = () => () => {
  return axiosClientCore.get(`${endpoints.documentReceiveMethod}`);
};

export const getAllCountries = () => async () => {
  const response = await axiosClientCore.get(`${endpoints.countries}`);
  return response.data;
};

export const getSettlementsAutocomplete = (inputValue, page, resultsLimit) => async () => {
  return commonCoreApiFilterFunction(endpoints.settlement.autocomplete, {
    name: inputValue,
    page: page,
    pageSize: resultsLimit,
  });
};

export const getSettlement = (id) => async () => {
  const response = await axiosClientCore.get(`${endpoints.settlement.baseUrl}/${id}`);
  return response.data;
};

export const getExtractedBirthDate = (personalId) => async () => {
  const response = await axiosClientCore.get(
    `${endpoints.person.baseUrl}${endpoints.person.extractDateFromCivilId}/${personalId}`
  );
  return response.data;
};

export const getReferenceDataOptions = (domain) => () => {
  return axiosClientCore.get(`${endpoints.referenceData}?domain=${domain}`);
};

export const getProfExperienceDocTypeOptions = () => () => {
  return axiosClientCore.get(`${endpoints.profExperienceDocType}`);
};

export const getLanguageOptions = () => () => {
  return axiosClientCore.get(`${endpoints.language}`);
};

export const getGraduationDocTypeOptions = () => () => {
  return axiosClientCore.get(`${endpoints.graduationDocType}`);
};

export const getProfGroupOptions = () => () => {
  return axiosClientCore.get(`${endpoints.profGroup}`);
};

export const getCfgEduLevels = () => () => {
  return axiosClientCore.get(`${endpoints.cfgEduLevel}`);
};

export const getCfgRecognitionCategories = () => () => {
  return axiosClientCore.get(`${endpoints.cfgRecognitionCategory}`);
};

export const getCfgGraduationWays = () => () => {
  return axiosClientCore.get(`${endpoints.cfgGraduationWay}`);
};

export const getCfgServiceTypes = () => () => {
  return axiosClientCore.get(`${endpoints.cfgServiceType}`);
};

export const uploadFile = (file, captchaToken, fileGroup) => () => {
  const formData = new FormData();
  formData.append("file", file, file.name);
  formData.append("captchaToken", captchaToken);
  formData.append("relativePath", ProcessEnvironments.FilePath.Services);
  if (fileGroup !== null) {
    formData.append("fileGroup", fileGroup);
  }
  const config = {
    headers: { "content-type": "multipart/form-data" },
  };
  return axiosClientCore.post(endpoints.file.upload, formData, config);
};

export const buildFetchFileUrl = (rootDirectory, relativePath, fileId) => {
  return `${ProcessEnvironments.Api.FrontOffice.Core}/api/v1${endpoints.file.get}?rootDirectory=${rootDirectory}&relativePath=${relativePath}&fileId=${fileId}`;
};

export const getAllNationalUniversities = () => async () => {
  const response = await axiosClientCore.get(`${endpoints.nationalUniversities}`);
  return response.data;
};

export const getServicesPageContent = () => () => {
  return axiosClientCore.get(`${endpoints.contentManagement}/homePageCategoryControl`);
};
