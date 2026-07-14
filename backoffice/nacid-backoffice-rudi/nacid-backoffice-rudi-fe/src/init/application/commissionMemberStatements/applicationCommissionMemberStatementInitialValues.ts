import { DocumentCategories, InitialValues } from "@duosoftbg/nacid-backoffice-components";
import { ApplicationCommissionMemberStatementDetails } from "../../../types/applications/common/commissionMemberStatements/applicationCommissionMemberStatementTypes";
import { ReferenceDataDomain } from "@duosoftbg/nacid-components";

export const applicationCommissionMemberStatementInitialValues: ApplicationCommissionMemberStatementDetails = {
  id: null,
  commissionMember: InitialValues.forms.commissionMember.commissionMembersInitialValues,
  attachedDoc: {
    ...InitialValues.forms.common.attachment.applicationAttachmentInitialValues,
    docCategory: {
      ...InitialValues.forms.referenceData.referenceDataInitialValues,
      id: DocumentCategories.COMMISSION_EXPERTS,
      domain: ReferenceDataDomain.DOC_CATEGORY,
    },
  },
};
