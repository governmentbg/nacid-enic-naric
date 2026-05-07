import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import { baseEndpointPaths } from "../services/serviceCalls";

export const getApplicationBaseUrl = (subtype: ApplicationSubtype) => {
  if (subtype) {
    switch (subtype) {
      case ApplicationSubtype.HE_RECOGNITION:
        return baseEndpointPaths.heRecognition;
      case ApplicationSubtype.UNI_CHECKS:
        return baseEndpointPaths.uniChecks;
      case ApplicationSubtype.DOC_DEGREES:
        return baseEndpointPaths.docDegrees;
      case ApplicationSubtype.REGULATED_PROFESSIONS:
        return baseEndpointPaths.regprof;
      case ApplicationSubtype.OFFICIAL_NOTE:
        return baseEndpointPaths.officialNotes;
      case ApplicationSubtype.BIBLIO_REFERENCE:
        return baseEndpointPaths.bibliographicReference;
      case ApplicationSubtype.INQUIRY:
        return baseEndpointPaths.inquiry;
      case ApplicationSubtype.DOCUMENT_SERVICE:
        return baseEndpointPaths.documentDelivery;
      case ApplicationSubtype.SIGNAL:
        return baseEndpointPaths.signal;
      case ApplicationSubtype.SUGGESTION:
        return baseEndpointPaths.suggestion;
      case ApplicationSubtype.PUBLIC_ACCESS:
        return baseEndpointPaths.publicAccess;
      default:
        return "";
    }
  }
};

export const createAppViewUrl = (id, subtype) => "/my-applications" + getApplicationBaseUrl(subtype) + `/view?id=${id}`;
export const createAppViewUrlWithServices = (id, subtype) => "/nacid-services" + createAppViewUrl(id, subtype);
export const createAppViewUrlWithBasePath = (id, basePath) => "/my-applications" + basePath + `/view?id=${id}`;
export const createAppEditUrl = (id, subtype) => "/my-applications" + getApplicationBaseUrl(subtype) + `/edit?id=${id}`;
export const createAppSignUrl = (id) => `/my-applications/app-sign?id=${id}`;
export const createMyApplicationsUrl = () => `/my-applications`;
