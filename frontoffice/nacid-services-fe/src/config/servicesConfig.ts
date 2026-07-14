import { ServiceConfig } from "../types/serviceTypes";
import { CONTENT_MANAGEMENT_ID } from "@duosoftbg/nacid-components";

export const officialNotesDissertationConfig: ServiceConfig = {
  baseHref: "/official-notes-dissertation",
  external: false,
  titleCode: "t.page.officialNotes.dissertation",
  descriptionCode: CONTENT_MANAGEMENT_ID.SD_OFFICIAL_NOTES_DISSERTATION_THESIS,
};

export const officialNotesPositionConfig: ServiceConfig = {
  baseHref: "/official-notes-position",
  external: false,
  titleCode: "t.page.officialNotes.position",
  descriptionCode: CONTENT_MANAGEMENT_ID.SD_OFFICIAL_NOTES_ACADEMIC_POSITION,
};

export const inquiryCitingsConfig: ServiceConfig = {
  baseHref: "/inquiry-citings",
  external: false,
  titleCode: "t.page.inquiry.citings",
  descriptionCode: CONTENT_MANAGEMENT_ID.SD_INQUIRY_PUBLICATION_CITINGS,
};

export const inquiryFactorConfig: ServiceConfig = {
  baseHref: "/inquiry-factor",
  external: false,
  titleCode: "t.page.inquiry.factor",
  descriptionCode: CONTENT_MANAGEMENT_ID.SD_INQUIRY_IMPACT_FACTOR,
};

export const biblioReferenceForeignConfig: ServiceConfig = {
  baseHref: "/bibliographic-reference-foreign",
  external: false,
  titleCode: "t.page.biblioReference.foreign",
  descriptionCode: CONTENT_MANAGEMENT_ID.SD_BIBLIOGRAPHIC_REFERENCES_FOREIGN_DBS,
};

export const biblioReferenceNacidConfig: ServiceConfig = {
  baseHref: "/bibliographic-reference-nacid",
  external: false,
  titleCode: "t.page.biblioReference.nacid",
  descriptionCode: CONTENT_MANAGEMENT_ID.SD_BIBLIOGRAPHIC_REFERENCES_NACID_DBS,
};

export const docDeliveryLibrariesConfig: ServiceConfig = {
  baseHref: "/document-delivery-libraries",
  external: false,
  titleCode: "t.page.docDelivery.libraries",
  descriptionCode: CONTENT_MANAGEMENT_ID.SD_DOCUMENT_DELIVERY_LIBRARIES,
};

export const docDeliveryNacidConfig: ServiceConfig = {
  baseHref: "/document-delivery-nacid",
  external: false,
  titleCode: "t.page.docDelivery.nacid",
  descriptionCode: CONTENT_MANAGEMENT_ID.SD_DOCUMENT_DELIVERY_NACID,
};

export const heRecognitionServiceConfig: ServiceConfig = {
  baseHref: "/he-recognition",
  external: false,
  titleCode: "t.page.higherEduRecognition",
  descriptionCode: CONTENT_MANAGEMENT_ID.SD_HIGHER_EDUCATION_RECOGNITION,
};

export const docDegreesDoctorServiceConfig: ServiceConfig = {
  baseHref: "/doc-degrees-doctor",
  external: false,
  titleCode: "t.page.doctorateDegrees.doctor",
  descriptionCode: CONTENT_MANAGEMENT_ID.SD_DOCTORATE_DEGREES_DOCTOR,
};

export const docDegreesDoctorOfScienceServiceConfig: ServiceConfig = {
  baseHref: "/doc-degrees-doctor-science",
  external: false,
  titleCode: "t.page.doctorateDegrees.doctorOfScience",
  descriptionCode: CONTENT_MANAGEMENT_ID.SD_DOCTORATE_DEGREES_DOCTOR_OF_SCIENCE,
};

export const regprofServiceConfig: ServiceConfig = {
  baseHref: "/regprof",
  external: false,
  titleCode: "t.page.regprof",
  descriptionCode: CONTENT_MANAGEMENT_ID.SD_NON_REGULATED_PROFESSIONS,
};

export const uniCheckStatusConfig: ServiceConfig = {
  baseHref: "/uni-checks-status",
  external: false,
  titleCode: "t.page.uniChecks.status",
  descriptionCode: CONTENT_MANAGEMENT_ID.SD_UNI_CHECKS_ACADEMIC_STATUS,
};

export const uniCheckAuthenticityConfig: ServiceConfig = {
  baseHref: "/uni-checks-authenticity",
  external: false,
  titleCode: "t.page.uniChecks.authenticity",
  descriptionCode: CONTENT_MANAGEMENT_ID.SD_UNI_CHECKS_DOCUMENT_AUTHENTICITY,
};

export const uniCheckRecommendationConfig: ServiceConfig = {
  baseHref: "/uni-checks-recommendation",
  external: false,
  titleCode: "t.page.uniChecks.recommendation",
  descriptionCode: CONTENT_MANAGEMENT_ID.SD_UNI_CHECKS_ISSUE_RECOMMENDATION,
};

export const suggestionConfig: ServiceConfig = {
  baseHref: "/suggestion",
  external: false,
  titleCode: "t.page.suggestion",
  descriptionCode: CONTENT_MANAGEMENT_ID.SD_SUGGESTION,
};

export const signalConfig: ServiceConfig = {
  baseHref: "/signal",
  external: false,
  titleCode: "t.page.signal",
  descriptionCode: CONTENT_MANAGEMENT_ID.SD_SIGNAL,
};

export const publicAccessConfig: ServiceConfig = {
  baseHref: "/public-access",
  external: false,
  titleCode: "t.page.publicAccess",
  descriptionCode: CONTENT_MANAGEMENT_ID.SD_PUBLIC_ACCESS,
};
