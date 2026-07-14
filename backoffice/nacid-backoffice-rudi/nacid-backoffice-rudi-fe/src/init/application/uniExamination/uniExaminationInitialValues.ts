import { DATE_FORMAT } from "@duosoftbg/nacid-components";
import { UniExaminationFormType } from "../../../types/applications/common/status/uniExaminationTypes";
import { format } from "date-fns";

export const uniExaminationFormInitialValues: UniExaminationFormType = {
  id: "",
  examinationDate: format(new Date(), DATE_FORMAT),
  isJointDegree: false,
  isCommunicated: false,
  isRecognized: false,
  notes: "",
  trainingLocationId: "",
  competentInstitutions: [],
  trainingForms: [],
  otherTrainingFormNote: "",
  attachedDocs: [],
};
