import { ApplicationAttachmentDetails, CompetentInstitutionDetails } from "@duosoftbg/nacid-backoffice-components";

export interface UniExaminationFormType {
  id: string;
  examinationDate: string;
  isJointDegree: boolean;
  isCommunicated: boolean;
  isRecognized: boolean;
  notes: string;
  trainingLocationId: string;
  competentInstitutions: Array<CompetentInstitutionDetails>;
  trainingForms: Array<string>;
  otherTrainingFormNote: string;
  attachedDocs: Array<ApplicationAttachmentDetails>;
}
