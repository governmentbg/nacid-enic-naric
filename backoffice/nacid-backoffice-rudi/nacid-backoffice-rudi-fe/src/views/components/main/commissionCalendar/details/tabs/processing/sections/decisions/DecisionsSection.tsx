import {
  AlertSpg,
  AsyncCallArgs,
  BoxSpg,
  CircularLoader,
  ConfirmSubmitDialog,
  FormSection,
  GridContainer,
  GridItem,
  isArrayEmpty,
  isArrayNotEmpty,
  isNotEmpty,
  ReloadWatcherObject,
  SelectFormField,
  useAsyncCall,
  useReactHookForm,
  useReloadWatcherWriter,
} from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import { useParams } from "react-router-dom";
import React, { useState } from "react";
import { faFile, faFileDownload } from "@fortawesome/free-solid-svg-icons";
import { FormProvider } from "react-hook-form";
import { Button, Chip } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import { toast } from "react-toastify";
import useApplicationIdsControl from "../../../../../hooks/useApplicationIdsControl";
import useAppDispatch from "../../../../../../../../../hooks/redux/base/useAppDispatch";
import { GlobalFileDetails } from "../../../../../../../../../types/commissionCalendar/commissionCalendarTypes";
import { commissionCalendarGlobalFileInitialValues } from "../../../../../../../../../init/commissionCalendar/commissionCalendarGlobalFileInitialValues";
import { createCommissionCalendarGlobalFileValidationSchema } from "../../../../../../../../../yup/schema/commissionCalendar/commissionCalendarGlobalFileValidationSchema";
import useAppSelector from "../../../../../../../../../hooks/redux/base/useAppSelector";
import { CoreApiServicesBase, DocumentTypes } from "@duosoftbg/nacid-backoffice-components";
import { generateGlobalReport, transferMissingAbdocsDocuments } from "../../../../../../../../../axios/api/services";
import { removeAll } from "../../../../../../../../../store/redux/slice/ComponentsControl/selectedIdsControl";
import { FilePresent } from "@mui/icons-material";
import ProcessingAppListTable from "../../ProcessingAppListTable";

const DecisionsSection = () => {
  const { t } = useTranslation();
  const calendarId = useParams().calendarId;
  const { applicationIds, error, loading } = useApplicationIdsControl({
    calendarId: calendarId,
  });

  if (loading) {
    return (
      <FormSection label={"t.commission.calendar.processing"}>
        <BoxSpg marginTop={3}>
          <CircularLoader />
        </BoxSpg>
      </FormSection>
    );
  }
  if (error) {
    return (
      <FormSection label={"t.commission.calendar.processing"}>
        <BoxSpg marginTop={3}>
          <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>
        </BoxSpg>
      </FormSection>
    );
  }
  return (
    <FormSection label={"t.commission.calendar.processing"}>
      <GenerateGlobalCertificatesComponent calendarId={calendarId} />
      <BoxSpg>
        {isArrayNotEmpty(applicationIds) && (
          <>
            <TransferMissingAbdocsDocumentsComponent calendarId={calendarId} />
            <ProcessingAppListTable calendarId={calendarId} applicationIds={applicationIds} />
          </>
        )}
        {isArrayEmpty(applicationIds) && <AlertSpg severity="info">{t("m.empty.list")}</AlertSpg>}
      </BoxSpg>
    </FormSection>
  );
};

export default DecisionsSection;

const TransferMissingAbdocsDocumentsComponent = ({ calendarId }) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const { updateReloadWatcher } = useReloadWatcherWriter();

  const transferDocuments = () => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: transferMissingAbdocsDocuments(calendarId),
      withGlobalBackdrop: true,
      onSuccess: (response) => {
        updateReloadWatcher(ReloadWatcherObject.build("calendarApplications", "reload"));
      },
    };
    asyncCall(asyncCallArgs);
  };

  return (
    <>
      <GridContainer>
        <GridItem sm={4} md={4} style={{ marginBottom: "10px" }}>
          <Button
            onClick={transferDocuments}
            style={{ marginRight: "10px" }}
            type={"button"}
            variant={"contained"}
            startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faFile} />}
          >
            {t("l.btn.transfer.missing.abdocs.documents.btn")}
          </Button>
        </GridItem>
      </GridContainer>
    </>
  );
};

const GenerateGlobalCertificatesComponent = ({ calendarId }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const { asyncCall } = useAsyncCall();
  const [errors, setErrors] = useState(null);
  const [confirmModalState, setConfirmModalState] = useState({ open: false, submitFn: null });
  const { updateReloadWatcher } = useReloadWatcherWriter();

  const { methods, handleSubmit } = useReactHookForm<GlobalFileDetails>({
    defaultValues: commissionCalendarGlobalFileInitialValues,
    validationSchema: createCommissionCalendarGlobalFileValidationSchema,
  });

  const { newIds } = useAppSelector((state) => {
    return state.ComponentsControl.selectedIdsControl;
  });

  const options = [
    { value: DocumentTypes.CERTIFICATE, text: t("l.certificateDocType") },
    { value: DocumentTypes.LETTER_TO_APPLICANT, text: t("l.letterToApplicantDocType") },
    { value: DocumentTypes.REJECTION_DECISION, text: t("l.rejection.decision") },
  ];

  const initOnGenerate = () => {
    methods.setValue(`attachments`, commissionCalendarGlobalFileInitialValues.attachments);
    methods.setValue(`isDraft`, commissionCalendarGlobalFileInitialValues.isDraft);
    methods.clearErrors();
  };

  const generateDraft = (values) => {
    initOnGenerate();
    onSubmit(values, true);
  };
  const generateGlobalFile = (values) => {
    initOnGenerate();
    onSubmit(values);
  };

  const handleAttachmentDownload = (attachment) => {
    window.open(
      CoreApiServicesBase.buildBoFetchFileUrl(attachment?.rootDirectory, attachment?.relativePath, attachment?.fileId),
      "_blank",
    );
  };
  const onSubmit = (values, isDraft = false) => {
    const asyncCreation: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: generateGlobalReport({ documentType: values.documentType, applicationIds: newIds, isDraft, calendarId }),
      reactHooksForm: { methods },
      onSuccess: (response) => {
        setErrors(null);
        let attachments = Object.values(response);
        methods.setValue(`attachments`, attachments);
        methods.setValue(`isDraft`, isDraft);
        methods.clearErrors();
        toast.success(t("m.file.successfully.attached"));
        dispatch(removeAll());
        updateReloadWatcher(ReloadWatcherObject.build("calendarApplications", "reload"));
      },
      onError: (response) => {
        initOnGenerate();
        setErrors(response.errors);
      },
    };
    if (isDraft) {
      asyncCall(asyncCreation);
    } else {
      setConfirmModalState({ open: true, submitFn: asyncCreation });
    }
  };

  return (
    <BoxSpg marginBottom={5}>
      <FormProvider {...methods}>
        <form>
          <GridContainer spacing={0}>
            <GridItem sm={4} md={4}>
              <SelectFormField
                fieldName={"documentType"}
                labelCode={"l.documentType"}
                selectOptions={options}
                addEmptyOption={false}
              />
            </GridItem>
            {isArrayNotEmpty(newIds) && (
              <>
                <GridItem sm={4} md={4}>
                  <Button
                    onClick={handleSubmit(generateGlobalFile)}
                    style={{ marginRight: "10px" }}
                    type={"button"}
                    variant={"contained"}
                    startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faFile} />}
                  >
                    {t("l.btn.generate.document")}
                  </Button>

                  <Button
                    onClick={handleSubmit(generateDraft)}
                    type={"button"}
                    variant={"contained"}
                    startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faFileDownload} />}
                  >
                    {t("l.btn.generate.draft.document")}
                  </Button>
                </GridItem>
              </>
            )}
            <GridItem sm={12} md={12}>
              {methods.getValues("attachments").map((attachment) => (
                <Chip
                  key={attachment?.fileId}
                  style={{ marginTop: "10px", marginRight: "5px" }}
                  icon={<FilePresent />}
                  label={attachment?.fileName}
                  onClick={() => {
                    handleAttachmentDownload(attachment);
                  }}
                />
              ))}

              {methods.getValues("isDraft") && (
                <AlertSpg style={{ backgroundColor: "transparent", paddingLeft: "0px" }} severity="info">
                  {t("m.calendar.draft.document")}
                </AlertSpg>
              )}

              <AlertSpg style={{ backgroundColor: "transparent", paddingLeft: "0px" }} severity="warning">
                {t("m.calendar.generate.documents")}
              </AlertSpg>

              {isNotEmpty(errors) && (
                <>
                  {errors.map((error) => (
                    <AlertSpg key={error.pointer} style={{ marginTop: "10px" }} severity="error">
                      {t(error.message)}
                    </AlertSpg>
                  ))}
                </>
              )}
            </GridItem>
          </GridContainer>
          <ConfirmSubmitDialog
            dialogTitleText={"m.calendar.generate.documents.title"}
            alertText={"m.calendar.generate.documents.dialog.text"}
            alertType={"warning"}
            modalState={confirmModalState}
            setModalState={setConfirmModalState}
          />
        </form>
      </FormProvider>
    </BoxSpg>
  );
};
