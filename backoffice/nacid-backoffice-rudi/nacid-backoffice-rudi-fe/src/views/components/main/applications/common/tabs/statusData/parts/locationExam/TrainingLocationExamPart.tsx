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
  i18nKeyByCode,
  ReloadWatcherObject,
} from "@duosoftbg/nacid-backoffice-components";
import * as React from "react";
import { useState } from "react";
import {
  getAppTrainingLocationExamData,
  saveAppTrainingLocationExamData,
} from "../../../../../../../../../axios/api/services";
import { useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { toast } from "react-toastify";
import { TrainingLocationExamFormType } from "../../../../../../../../../types/applications/common/status/trainingLocationExamTypes";
import { trainingLocationExamFormInitialValues } from "../../../../../../../../../init/application/trainingLocationExam/trainingLocationExamInitialValues";
import UniversitySections from "./sections/university/UniversitySections";
import LegitimacySection from "./sections/legitamicy/LegitimacySection";
import TrainingLocationSections from "./sections/location/TrainingLocationSections";
import SaveTrainingInstitutionDialog from "../../../../components/SaveTrainingInstitutionDialog";
import StatusUpdateAlert from "../../../../status/StatusUpdateAlert";
import { vTrainingLocationExamSchema } from "../../../../../../../../../yup/schema/applications/common/schemas";

const TrainingLocationExamPart = ({ appType }) => {
  const { id: applicationId } = useParams();
  const tempDataKey = `${appType}-${applicationId}`;

  const { t } = useTranslation();
  const { scrollToTop } = useScroll();
  const { updateReloadWatcher } = useReloadWatcherWriter();

  const [confirmModalState, setConfirmModalState] = useState({ open: false, submitFn: null });
  const { methods, handleSubmit } = useReactHookForm<TrainingLocationExamFormType>({
    defaultValues: trainingLocationExamFormInitialValues,
    validationSchema: vTrainingLocationExamSchema,
  });

  useFormDirtyStateSetter({ methods });
  useScrollOnValidationError({ methods });

  const onSubmit = () => {
    const asyncCreation: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: saveAppTrainingLocationExamData(applicationId, methods.getValues()),
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
        <AppSectionTitle title={i18nKeyByCode(appType, "t.appSubSections.trainingLocationExam")} />
      </BoxSpg>
      <BoxSpg>
        <SaveTrainingInstitutionDialog resetExternalField={true} tempFormDataKey={tempDataKey} />
        <ApplicationSectionFormInitializer
          methods={methods}
          onSubmit={handleSubmit(onSubmit)}
          dataFunction={() => getAppTrainingLocationExamData(applicationId)}
        >
          <ValidationErrors />
          <StatusUpdateAlert />
          <UniversitySections applicationId={applicationId} appType={appType} />
          <LegitimacySection />
          <TrainingLocationSections tempDataKey={tempDataKey} appType={appType} />
          <ConfirmSubmitDialog modalState={confirmModalState} setModalState={setConfirmModalState} />
        </ApplicationSectionFormInitializer>
      </BoxSpg>
    </>
  );
};

export default TrainingLocationExamPart;
