import {
  AsyncCallArgs,
  BoxSpg,
  ConfirmSubmitDialog,
  useFormDirtyStateSetter,
  useReactHookForm,
  useReloadWatcherWriter,
  useScroll,
  useScrollOnValidationError,
  ValidationErrors,
} from "@duosoftbg/nacid-components";
import {
  ApplicationSectionFormInitializer,
  AppSectionTitle,
  ReloadWatcherObject,
} from "@duosoftbg/nacid-backoffice-components";
import * as React from "react";
import { useParams } from "react-router-dom";
import { getAppProgramExamData, saveAppProgramExamData } from "../../../../../../../../../axios/api/services";
import { useState } from "react";
import { programExamFormInitialValues } from "../../../../../../../../../init/application/programExam/programExamInitialValues";
import { ProgramExamFormType } from "../../../../../../../../../types/applications/common/status/programExamTypes";
import { toast } from "react-toastify";
import { useTranslation } from "react-i18next";
import StatusUpdateAlert from "../../../../status/StatusUpdateAlert";
import LegitimacySection from "./sections/LegitamicySection";
import { vProgramExamSchema } from "../../../../../../../../../yup/schema/applications/common/schemas";

const ProgramExamPart = () => {
  const { id: applicationId } = useParams();

  const { t } = useTranslation();
  const { scrollToTop } = useScroll();
  const { updateReloadWatcher } = useReloadWatcherWriter();
  const [confirmModalState, setConfirmModalState] = useState({ open: false, submitFn: null });
  const { methods, handleSubmit } = useReactHookForm<ProgramExamFormType>({
    defaultValues: programExamFormInitialValues,
    validationSchema: vProgramExamSchema,
  });

  useFormDirtyStateSetter({ methods });
  useScrollOnValidationError({ methods });

  const onSubmit = () => {
    const asyncCreation: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: saveAppProgramExamData(applicationId, methods.getValues()),
      processResponseErrors: true,
      reactHooksForm: { methods },
      onSuccess: (response) => {
        methods.reset(response);

        if (response?.isStatusUpdated === true) {
          updateReloadWatcher(ReloadWatcherObject.Application.id(applicationId));
        }
        toast.success(t("m.save.data.success"));
        scrollToTop(true);
      },
    };
    setConfirmModalState({ open: true, submitFn: asyncCreation });
  };

  return (
    <>
      <BoxSpg>
        <AppSectionTitle title={"t.appSubSections.programExam"} />
      </BoxSpg>
      <BoxSpg>
        <ApplicationSectionFormInitializer
          methods={methods}
          onSubmit={handleSubmit(onSubmit)}
          dataFunction={() => getAppProgramExamData(applicationId)}
        >
          <ValidationErrors />
          <StatusUpdateAlert />
          <LegitimacySection />
          <ConfirmSubmitDialog modalState={confirmModalState} setModalState={setConfirmModalState} />
        </ApplicationSectionFormInitializer>
      </BoxSpg>
    </>
  );
};

export default ProgramExamPart;
