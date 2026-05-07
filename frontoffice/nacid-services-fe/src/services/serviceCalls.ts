import { axiosClientServices as axiosClient, ProcessEnvironments } from "@duosoftbg/nacid-frontoffice-components";

export const baseEndpointPaths = {
  heRecognition: "/he-recognition",
  docDegrees: "/doc-degrees",
  uniChecks: "/uni-checks",
  regprof: "/regprof",
  bibliographicReference: "/bibliographic-reference",
  documentDelivery: "/document-delivery",
  inquiry: "/inquiry",
  officialNotes: "/official-notes",
  signal: "/signal",
  suggestion: "/suggestion",
  publicAccess: "/public-access",
};

const relativeEndpoints = {
  saveApplicantDetails: "/save-applicant-details",
  saveSpecificDetails: "/save-app-specific-details",
  saveDocumentDetails: "/save-document-details",
  getApplication: "/",
  finalizeApplication: "/finalize-application",
  fileApplication: "/file-application",
  fileSignedApplication: "/file-signed-application",
  evaluateApplication: "/evaluate-application",
  deleteApplication: "/",
  calculateFees: "/calculate-fees",
  draftReceipt: "/generate-draft-receipt",
  checkup: "/checkup",
};

const uniChecksEndpoints = {
  calculateUniCheckFees: "/calculate-uni-checks-fees",
};

const regprofEndpoints = {
  calculateRegprofFees: "/calculate-regprof-fees",
};

const officialNotesEndpoints = {
  calculateOfficialNotesFees: "/calculate-official-notes-fees",
};

export const saveApplicantDetails = (basePath, id, applicantDetails) => () => {
  return axiosClient.post(`${basePath}${relativeEndpoints.saveApplicantDetails}`, applicantDetails, {
    params: { id: id },
  });
};

export const saveSpecificDetails = (basePath, id, educationDetails) => () => {
  return axiosClient.post(`${basePath}${relativeEndpoints.saveSpecificDetails}`, educationDetails, {
    params: { id: id },
  });
};

export const saveDocumentDetails = (basePath, id, documentDetails) => () => {
  return axiosClient.post(`${basePath}${relativeEndpoints.saveDocumentDetails}`, documentDetails, {
    params: { id: id },
  });
};

export const getApplicationById = (basePath, id) => () => {
  return axiosClient.get(`${basePath}${relativeEndpoints.getApplication}${id}`);
};

export const getApplicationForCheckup = (basePath, dossierNumber, accessCode, captchaToken) => () => {
  return axiosClient.get(`${basePath}${relativeEndpoints.checkup}`, {
    params: { dossierNumber, accessCode, captchaToken },
  });
};

export const finalizeApplicationForId = (basePath, id) => () => {
  return axiosClient.post(`${basePath}${relativeEndpoints.finalizeApplication}`, null, { params: { id: id } });
};

export const fileApplicationForId = (basePath, id) => () => {
  return axiosClient.post(`${basePath}${relativeEndpoints.fileApplication}`, null, { params: { id: id } });
};
export const fileSignedApplicationForId = (basePath, id, signedData) => () => {
  return axiosClient.post(`${basePath}${relativeEndpoints.fileSignedApplication}`, signedData, { params: { id: id } });
};

export const evaluateApplicationForId = (basePath, id) => () => {
  return axiosClient.get(`${basePath}${relativeEndpoints.evaluateApplication}?id=${id}`);
};

export const deleteApplicationForId = (basePath, id) => () => {
  return axiosClient.delete(`${basePath}${relativeEndpoints.deleteApplication}${id}`);
};

export const calculateFeesForId = (basePath, id) => () => {
  return axiosClient.get(`${basePath}${relativeEndpoints.calculateFees}?id=${id}`);
};

export const downloadDraftReceiptForId = (basePath, id) => () => {
  return axiosClient.get(
    `${ProcessEnvironments.Api.FrontOffice.Services}/api/v1${basePath}${relativeEndpoints.draftReceipt}?id=${id}`,
    {
      responseType: "blob",
    }
  );
};

export const calculateUniChecksFees = (statute, authenticity, recommendation, serviceType, applicantType) => () => {
  return axiosClient.get(
    `${baseEndpointPaths.uniChecks}${uniChecksEndpoints.calculateUniCheckFees}?statute=${statute}&authenticity=${authenticity}&recommendation=${recommendation}&serviceType=${serviceType}&applicantType=${applicantType}`
  );
};

export const calculateRegprofFees = (serviceType) => () => {
  return axiosClient.get(
    `${baseEndpointPaths.regprof}${regprofEndpoints.calculateRegprofFees}?serviceType=${serviceType}`
  );
};
export const calculateOfficialNotesFees = (kinds, serviceType) => () => {
  return axiosClient.get(
    `${baseEndpointPaths.officialNotes}${
      officialNotesEndpoints.calculateOfficialNotesFees
    }?serviceType=${serviceType}&kinds=${kinds.join(";")}`
  );
};
