import { ApplicationAttachmentDetails, CommissionMembersDetails } from "@duosoftbg/nacid-backoffice-components";

export interface ApplicationCommissionMemberStatementDetails {
  id: number;
  commissionMember: CommissionMembersDetails;
  attachedDoc: ApplicationAttachmentDetails;
}
