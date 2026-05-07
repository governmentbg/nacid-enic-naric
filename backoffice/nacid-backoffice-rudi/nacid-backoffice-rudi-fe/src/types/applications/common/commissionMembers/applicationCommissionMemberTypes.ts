import {
  CommissionMemberPositionDetails,
  CommissionMembersDetails,
  LegalReasonDetails,
  ReferenceDataDetails,
} from "@duosoftbg/nacid-backoffice-components";

export interface ApplicationCommissionMemberDetails {
  id: number;
  commissionMember: CommissionMembersDetails;
  notes: string;
  courseContent: string;
  qualification: string;
  previousBoardDecisions: string;
  similarBulgarianPrograms: string;
  eduLevel: ReferenceDataDetails;
  commissionMemberPosition: CommissionMemberPositionDetails;
  legalReason: LegalReasonDetails;
  processStatus: boolean;
  applicationCommissionMemberSpecialities: ApplicationCommissionMemberSpecialityDetails[];
  specialities: string[];
  qualificationObject: any;
}

export interface ApplicationCommissionMemberSpecialityDetails {
  id: number;
  speciality: string;
}
