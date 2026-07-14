import {
  BoxSpg,
  DividerSpg,
  SecurityGuard,
  SecurityRole,
  SubmitFormButton,
  useFormDirtyStateSetter,
  useReactHookForm,
} from "@duosoftbg/nacid-components";
import {
  AppSectionTitle,
  AppType,
  FinalizedAttachedDocsSection,
  StatusDataFormInitializer,
  StatusHistorySection,
} from "@duosoftbg/nacid-backoffice-components";
import * as React from "react";
import StatusSection from "./sections/status/StatusSection";
import { useParams } from "react-router-dom";
import { getApplicationStatusData, updateStatusData } from "../../../../../../../../../axios/api/services";
import { StatusDetails } from "../../../../../../../../../types/applications/common/status/statusTypes";
import { vStatusDataSchema } from "../../../../../../../../../yup/schema/applications/common/schemas";
import { statusInitialValues } from "../../../../../../../../../init/application/status/statusInitialValues";

const StatusPart = ({ appType }) => {
  const { id: applicationId } = useParams();

  const { methods, handleSubmit } = useReactHookForm<StatusDetails>({
    defaultValues: statusInitialValues,
    validationSchema: vStatusDataSchema,
  });
  useFormDirtyStateSetter({ methods });

  return (
    <BoxSpg>
      <AppSectionTitle title={"t.appSubSections.status"} />
      <BoxSpg>
        {AppType.SAR_APPLICATION !== appType && <FinalizedAttachedDocsSection />}
        <StatusDataFormInitializer
          statusDataFn={getApplicationStatusData}
          updateStatusDataFn={updateStatusData}
          methods={methods}
          handleSubmit={handleSubmit}
        >
          <StatusSection appType={appType} applicationId={applicationId} />
          <SecurityGuard checkForRoles={[SecurityRole.StatusEdit]}>
            <DividerSpg my={4} />
            <BoxSpg>
              <SubmitFormButton withLoader withLoadingText label={"l.btn.save.data"} color="primary" />
            </BoxSpg>
          </SecurityGuard>
          <StatusHistorySection />
        </StatusDataFormInitializer>
      </BoxSpg>
    </BoxSpg>
  );
};

export default StatusPart;
