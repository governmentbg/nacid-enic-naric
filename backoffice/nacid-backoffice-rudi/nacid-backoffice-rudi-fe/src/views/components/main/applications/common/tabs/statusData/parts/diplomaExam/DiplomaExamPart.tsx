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
  AbdocsTransferAttachmentsDialog,
  ApplicationSectionFormInitializer,
  AppSectionTitle,
  ReloadWatcherObject,
  SaveAttachmentDialog,
  SaveCompetentInstitutionDialog,
  ViewCompetentInstitutionDialog,
} from "@duosoftbg/nacid-backoffice-components";
import * as React from "react";
import { useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { useState } from "react";
import { DiplomaExamFormType } from "../../../../../../../../../types/applications/common/status/diplomaExamTypes";
import { diplomaExamFormInitialValues } from "../../../../../../../../../init/application/diplomaExam/diplomaExamInitialValues";
import { getAppDiplomaExamData, saveAppDiplomaExamData } from "../../../../../../../../../axios/api/services";
import { toast } from "react-toastify";
import StatusUpdateAlert from "../../../../status/StatusUpdateAlert";
import InfoSourcesSection from "./sections/infoSources/InfoSourcesSection";
import ExaminationSection from "./sections/exam/ExaminationSection";
import { vDiplomaExamSchema } from "../../../../../../../../../yup/schema/applications/common/schemas";
import DiplomaExamAttachmentsSection from "./sections/attachment/DiplomaExamAttachmentsSection";

const DiplomaExamPart = ({ appType }) => {
  const { id: applicationId } = useParams();
  const tempDataKey = `${appType}-${applicationId}`;
  const competentInstitutionPointer = "competentInstitutionId";

  const { t } = useTranslation();
  const { scrollToTop } = useScroll();
  const { updateReloadWatcher } = useReloadWatcherWriter();
  const [confirmModalState, setConfirmModalState] = useState({ open: false, submitFn: null });
  const { methods, handleSubmit } = useReactHookForm<DiplomaExamFormType>({
    defaultValues: diplomaExamFormInitialValues,
    validationSchema: vDiplomaExamSchema,
  });
  useFormDirtyStateSetter({ methods });
  useScrollOnValidationError({ methods });

  const onSubmit = () => {
    const asyncCreation: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: saveAppDiplomaExamData(applicationId, methods.getValues()),
      processResponseErrors: true,
      reactHooksForm: { methods },
      onSuccess: (response) => {
        methods.reset(response);

        if (response?.isStatusUpdated === true) {
          updateReloadWatcher(ReloadWatcherObject.Application.id(applicationId));
        }
        updateReloadWatcher(ReloadWatcherObject.AbdocsTransferConfig.reload());
        toast.success(t("m.save.data.success"));
        scrollToTop(true);
      },
    };
    setConfirmModalState({ open: true, submitFn: asyncCreation });
  };

  return (
    <>
      <BoxSpg>
        <AppSectionTitle title={"t.appSubSections.diplomaExam"} />
      </BoxSpg>
      <BoxSpg>
        <SaveAttachmentDialog />
        <AbdocsTransferAttachmentsDialog />
        <SaveCompetentInstitutionDialog
          resetExternalField={true}
          tempFormDataKey={tempDataKey}
          fieldId={competentInstitutionPointer}
        />
        <ApplicationSectionFormInitializer
          methods={methods}
          onSubmit={handleSubmit(onSubmit)}
          dataFunction={() => getAppDiplomaExamData(applicationId)}
        >
          <ValidationErrors />
          <ViewCompetentInstitutionDialog />
          <StatusUpdateAlert />
          <InfoSourcesSection
            appType={appType}
            tempDataKey={tempDataKey}
            competentInstitutionPointer={competentInstitutionPointer}
          />
          <ExaminationSection appType={appType} />
          <DiplomaExamAttachmentsSection applicationId={applicationId} tempDataKey={tempDataKey} />
          <ConfirmSubmitDialog modalState={confirmModalState} setModalState={setConfirmModalState} />
        </ApplicationSectionFormInitializer>
      </BoxSpg>
    </>
  );
};

export default DiplomaExamPart;
