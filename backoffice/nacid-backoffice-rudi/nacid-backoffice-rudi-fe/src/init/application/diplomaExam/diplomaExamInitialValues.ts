import { DiplomaExamFormType } from "../../../types/applications/common/status/diplomaExamTypes";

export const diplomaExamFormInitialValues: DiplomaExamFormType = {
  applicationId: "",
  examinationDate: "",
  notes: "",
  isAuthentic: false,
  isInstitutionCommunicated: false,
  isUniversityCommunicated: false,
  isFoundInRegister: false,
  isStateApproved: false,
  competentInstitutionId: "",
  universityCountryIds: [],
  universityNames: [],
};
