export interface DiplomaExamFormType {
  applicationId: string;
  examinationDate: string;
  notes: string;
  isAuthentic: boolean;
  isInstitutionCommunicated: boolean;
  isUniversityCommunicated: boolean;
  isFoundInRegister: boolean;
  isStateApproved: boolean;
  competentInstitutionId: string;
  universityCountryIds: Array<string>;
  universityNames: Array<string>;
}
