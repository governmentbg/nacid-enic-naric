import {
  AsyncCallArgs,
  CircularLoader,
  FileDetails,
  FileUploadButton,
  FileUploadHelperText,
  FormDirtyStateControlActions,
  FormSection,
  GridContainer,
  isArrayNotEmpty,
  SecurityGuard,
  SecurityRole,
  useAsyncCall,
  useReactHookForm,
} from "@duosoftbg/nacid-components";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { CommissionCalendarProtocolDetails } from "../../../../../../../../../types/commissionCalendar/commissionCalendarTypes";
import { commissionCalendarProtocolInitialValues } from "../../../../../../../../../init/commissionCalendar/commissionCalendarProtocolInitialValues";
import { createCommissionCalendarProtocolValidationSchema } from "../../../../../../../../../yup/schema/commissionCalendar/commissionCalendarProtocolValidationSchema";
import { FormProvider } from "react-hook-form";
import { CalendarTemplates, ErrorMessages, MinioRootDirectory } from "../../../../../../../../../utils/constants";
import { Button, Typography } from "@mui/material";
import { useTranslation } from "react-i18next";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFileDownload } from "@fortawesome/free-solid-svg-icons";
import {
  CoreApiServicesBase,
  handleCommonAttachmentDownload,
  ReportType,
} from "@duosoftbg/nacid-backoffice-components";
import { toast } from "react-toastify";
import {
  getCalendarProtocols,
  getCommissionCalendarFullNumber,
  updateCalendarProtocols,
} from "../../../../../../../../../axios/api/services";
import useCalendarGenerateFile from "../../../../../hooks/useCalendarGenerateFile";
import useAppDispatch from "../../../../../../../../../hooks/redux/base/useAppDispatch";

const CalendarAttachment = () => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const dispatch = useAppDispatch();
  const params = useParams();
  const calendarId = params.calendarId;
  const [protocolLoading, setProtocolLoading] = useState(true);

  const { generateFile } = useCalendarGenerateFile({
    calendarId: calendarId,
  });
  const [uploadErrors, setUploadErrors] = useState<any>(null);

  const { methods, handleSubmit } = useReactHookForm<CommissionCalendarProtocolDetails>({
    defaultValues: commissionCalendarProtocolInitialValues,
    validationSchema: createCommissionCalendarProtocolValidationSchema,
  });
  const [fullNumber, setFullNumber] = useState(null);

  useEffect(() => {
    if (calendarId) {
      const asyncCallArgs: AsyncCallArgs = {
        promise: getCommissionCalendarFullNumber(calendarId),
        processResponseErrors: false,
        onSuccess: (response) => {
          setFullNumber(response);
        },
      };
      asyncCall(asyncCallArgs);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [calendarId]);

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getCalendarProtocols(calendarId),
      processResponseErrors: false,
      onSuccess: (response) => {
        methods.setValue("attachment", response.commissionProtocol);
        methods.setValue("scannedAttachment", response.scannedCommissionProtocol);
        setProtocolLoading(false);
      },
    };
    asyncCall(asyncCallArgs);

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [calendarId]);

  const onProtocolChangeSuccess = (response, fieldName) => {
    const file = {
      id: "",
      fileName: response.fileName,
      fileSize: response.fileSize,
      contentType: response.contentType,
      fileLocation: `${response.relativePath}/${response.fileId}`,
      bucketName: `${response.rootDirectory}`,
    };
    methods.setValue(fieldName, file);
    toast.success(t("m.file.successfully.attached"));
    dispatch(FormDirtyStateControlActions.setDirty({ dirty: true }));
  };

  function onProtocolFileChange(e) {
    const uploadFile: AsyncCallArgs = {
      withGlobalBackdrop: true,
      reactHooksForm: { methods },
      promise: CoreApiServicesBase.uploadFileRestricted(
        "commission-calendar",
        e.target.files[0],
        "doc",
        "attachment",
        MinioRootDirectory,
      ),
      onSuccess: (response) => {
        onProtocolChangeSuccess(response, "attachment");
        setUploadErrors(null);
      },
      onError: (error) => {
        setUploadErrors(error.errors);
      },
    };
    asyncCall(uploadFile);
  }

  function onScannedProtocolFileChange(e) {
    const uploadFile: AsyncCallArgs = {
      withGlobalBackdrop: true,
      reactHooksForm: { methods },
      promise: CoreApiServicesBase.uploadFileRestricted(
        "commission-calendar",
        e.target.files[0],
        "pdf",
        "scannedAttachment",
        MinioRootDirectory,
      ),
      onSuccess: (response) => {
        onProtocolChangeSuccess(response, "scannedAttachment");
        setUploadErrors(null);
      },
      onError: (error) => {
        setUploadErrors(error.errors);
      },
    };
    asyncCall(uploadFile);
  }

  const onSubmit = (values) => {
    const asyncCallArgs: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: updateCalendarProtocols(calendarId, values.attachment, values.scannedAttachment),
      reactHooksForm: { methods },
      onSuccess: (response) => {
        methods.setValue("attachment", response.commissionProtocol);
        methods.setValue("scannedAttachment", response.scannedCommissionProtocol);
        dispatch(FormDirtyStateControlActions.setDirty({ dirty: false }));
        toast.success(t("m.save.data.success"));
      },
    };
    asyncCall(asyncCallArgs);
  };
  return (
    <FormSection label={"t.commission.calendar.attachments"}>
      <FormProvider {...methods}>
        <form onSubmit={handleSubmit(onSubmit)}>
          <GridContainer>
            <Button
              style={{ marginRight: "10px" }}
              startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faFileDownload} />}
              size={"medium"}
              type={"button"}
              variant="contained"
              color="primary"
              onClick={() => {
                generateFile(
                  CalendarTemplates.XLSX_REPORT_TEMPLATE,
                  ReportType.XLSX.key,
                  "commission_calendar_report.xlsx",
                );
              }}
            >
              {t("l.btn.generate.calendar.xlsx")}
            </Button>
            <Button
              style={{ marginRight: "10px" }}
              startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faFileDownload} />}
              size={"medium"}
              type={"button"}
              variant="contained"
              color="primary"
              onClick={() => {
                generateFile(
                  CalendarTemplates.PROTOCOL_TEMPLATE,
                  ReportType.DOCX.key,
                  "commission_session_protocol_" + fullNumber + ".docx",
                );
              }}
            >
              {t("l.btn.generate.calendar.protocol")}
            </Button>

            <SecurityGuard checkForRoles={[SecurityRole.CommissionCalendarDecisionsProtocolUpload]}>
              <div style={{ marginRight: "10px" }}>
                <FileUploadButton
                  required={false}
                  label={"l.btn.upload.calendar.protocol"}
                  accept={"application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document"}
                  onFileChange={(e) => onProtocolFileChange(e)}
                  id={"calendar-protocol-button"}
                />
              </div>

              <FileUploadButton
                required={false}
                label={"l.btn.upload.calendar.scanned.protocol"}
                accept={"application/pdf"}
                onFileChange={(e) => onScannedProtocolFileChange(e)}
                id={"calendar-scanned-protocol-button"}
              />
            </SecurityGuard>
          </GridContainer>

          <SecurityGuard checkForRoles={[SecurityRole.CommissionCalendarDecisionsProtocolUpload]}>
            <GridContainer>
              <FileUploadHelperText>{t("m.calendar.protocols.max.size.message")}</FileUploadHelperText>
            </GridContainer>
          </SecurityGuard>

          {!protocolLoading && (
            <>
              <FileDetails
                field={"attachment"}
                handleClick={() => {
                  handleCommonAttachmentDownload(methods.getValues("attachment"));
                }}
              />

              <FileDetails
                field={"scannedAttachment"}
                handleClick={() => {
                  handleCommonAttachmentDownload(methods.getValues("scannedAttachment"));
                }}
              />
            </>
          )}

          {protocolLoading && <CircularLoader />}

          <GridContainer>{isArrayNotEmpty(uploadErrors) && <ErrorMessages errors={uploadErrors} />}</GridContainer>
          <SecurityGuard checkForRoles={[SecurityRole.CommissionCalendarDecisionsProtocolUpload]}>
            <Typography align={"right"}>
              <Button type={"submit"} variant={"contained"}>
                {t("l.btn.save.protocol")}
              </Button>
            </Typography>
          </SecurityGuard>
        </form>
      </FormProvider>
    </FormSection>
  );
};

export default CalendarAttachment;
