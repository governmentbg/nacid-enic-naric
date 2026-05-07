import { InitialValues } from "@duosoftbg/nacid-backoffice-components";
import { ApplicationCommissionMemberDetails } from "../../../types/applications/common/commissionMembers/applicationCommissionMemberTypes";

export const qualificationObjectInitValues = {
  id: "",
  name: "",
};

export const applicationCommissionMemberInitialValues: ApplicationCommissionMemberDetails = {
  id: null,
  commissionMember: InitialValues.forms.commissionMember.commissionMembersInitialValues,
  notes: "",
  courseContent: "",
  qualification: "",
  previousBoardDecisions: "",
  similarBulgarianPrograms: "",
  eduLevel: null,
  commissionMemberPosition: InitialValues.forms.commissionMemberPosition.commissionMemberPositionInitialValues,
  legalReason: null,
  processStatus: false,
  applicationCommissionMemberSpecialities: [],
  specialities: [],
  qualificationObject: qualificationObjectInitValues,
};
