import { CommissionMembersDetails, ReferenceDataDetails } from "@duosoftbg/nacid-backoffice-components";
import { AttachmentProps, ReferenceData } from "@duosoftbg/nacid-components";

export interface CommissionCalendarFilterDetails {
  sessionNum: string;
  sessionStatusCode: string;
  sessionTimeFrom: string;
  sessionTimeTo: string;
  page: number;
  pageSize: number;
  order: string;
  orderBy: string;
}

export interface CommissionCalendarDetails {
  id: number;
  sessionNum: string;
  sessionTime: string;
  notes: string;
  status: ReferenceData;
}

export interface CommissionParticipationDetails {
  commissionMember: CommissionMembersDetails;
  notified: boolean;
  participated: boolean;
}

export interface ProcessDataDetails {
  applicationId: number;
  calendarId: number;
  motives: string;
  applicantInfo: string;
  recognizedEduLevel: string;
  recognizedQualification: string;
  recognizedProfGroupId: string;
  statusCode: string;
  legalReasonId: string;
  specialities: String[];
}

export interface SecretaryDataDetails {
  responsibleUser: string;
}

export interface GlobalFileDetails {
  documentType: number;
  attachments: any;
  isDraft: boolean;
}

export interface CommissionCalendarProtocolDetails {
  attachment: AttachmentProps;
  scannedAttachment: AttachmentProps;
}
