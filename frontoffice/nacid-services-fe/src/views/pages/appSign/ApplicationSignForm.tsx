import { useTranslation } from "react-i18next";
import {
  AlertSpg,
  AsyncCallArgs,
  BoxSpg,
  CircularTextLoader,
  FileUploadButton,
  getFieldError,
  GridContainer,
  GridItem,
  shouldShowFieldError,
  useAsyncCall,
  useReactHookForm,
  ButtonSpg,
  SimpleConfirmDialog,
} from "@duosoftbg/nacid-components";
import { useNavigate, useSearchParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { SignedApplicationDocument } from "../../../types/common/documentTypes";
import { initialSignedApplicationDocument } from "../../../init/common/documentInitialValues";
import { createSignedApplicationDocumentValidationSchema } from "../../../yup/common/document/documentValidationSchemas";
import { buildFetchFileUrl } from "../../../services/coreServicesCalls";
import { FormProvider } from "react-hook-form";
import { Alert, Button } from "@mui/material";
import FileDetails from "../../components/services/common/form/document/FileDetails";
import { toast } from "react-toastify";
import { fetchAppSignDetails, uploadSignedFile } from "../../../services/appSignCalls";
import { fileApplicationForId, fileSignedApplicationForId } from "../../../services/serviceCalls";
import { Download } from "@mui/icons-material";
import { createAppViewUrlWithBasePath, getApplicationBaseUrl } from "../../../utils/applicationUrlUtils";

const ApplicationSignForm = () => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");
  const [appToSignDetails, setAppToSignDetails] = useState({
    details: null,
    loading: true,
    fail: null,
  });
  const [unsignedDialogOpen, setUnsignedDialogOpen] = useState(false);

  const { methods } = useReactHookForm<SignedApplicationDocument>({
    defaultValues: initialSignedApplicationDocument,
    validationSchema: createSignedApplicationDocumentValidationSchema,
  });

  const { reset } = methods;

  useEffect(() => {
    reset(initialSignedApplicationDocument);
  }, [reset]);

  useEffect(() => {
    const fetchDetails: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: fetchAppSignDetails(id),
      processResponseErrors: false,
      onSuccess: (response) => {
        setAppToSignDetails({ details: response.data, loading: false, fail: null });
      },
      onError: (errResponse) => {
        setAppToSignDetails({ details: null, loading: false, fail: errResponse });
      },
    };
    asyncCall(fetchDetails);
  }, [id, asyncCall]);

  const onFileChange = async (e) => {
    const uploadAttachmentFile: AsyncCallArgs = {
      promise: uploadSignedFile(e.target.files[0]),
      withGlobalBackdrop: true,
      reactHooksForm: { methods },
      onSuccess: (response) => {
        const file = { ...response.data };
        methods.setValue("file", file);
        methods.clearErrors("file");
      },
    };
    asyncCall(uploadAttachmentFile);
  };

  const onSubmit = (values) => {
    const basePath = getApplicationBaseUrl(appToSignDetails.details.applicationSubtype);
    const fileApplicationAsyncCall: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: fileSignedApplicationForId(basePath, id, values),
      reactHooksForm: { methods },
      processResponseErrors: true,
      onSuccess: (response) => {
        navigate(createAppViewUrlWithBasePath(id, basePath));
        toast.success(t("m.filing.application.success", { tempNumber: response.data }), { autoClose: 10000 });
      },
    };
    asyncCall(fileApplicationAsyncCall);
  };

  const fileUnsignedApplication = () => {
    const basePath = getApplicationBaseUrl(appToSignDetails.details.applicationSubtype);
    const fileApplicationAsyncCall: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: fileApplicationForId(basePath, id),
      reactHooksForm: { methods },
      processResponseErrors: false,
      onSuccess: (response) => {
        navigate(createAppViewUrlWithBasePath(id, basePath));
        toast.success(t("m.filing.application.success", { tempNumber: response.data }), { autoClose: 10000 });
      },
      onError: () => {
        toast.error(t("m.generic.error.service.fail"));
      },
    };
    asyncCall(fileApplicationAsyncCall);
  };

  if (appToSignDetails.loading) {
    return <CircularTextLoader />;
  } else if (appToSignDetails.fail != null) {
    return (
      <BoxSpg>
        <AlertSpg severity={"error"}>{appToSignDetails.fail.message && t(appToSignDetails.fail.message)}</AlertSpg>
      </BoxSpg>
    );
  }
  return (
    <BoxSpg>
      <GridContainer spacing={5} mt={0}>
        <GridItem sm={12} md={12}>
          <AlertSpg severity={"info"}>{t("m.esign.instructions.step1")}</AlertSpg>
        </GridItem>
        <GridItem sm={12} md={12}>
          <ButtonSpg
            variant={"contained"}
            color={"secondary"}
            onClick={() =>
              window.open(
                buildFetchFileUrl(
                  appToSignDetails.details.receipt.rootDirectory,
                  appToSignDetails.details.receipt.relativePath,
                  appToSignDetails.details.receipt.fileId
                ),
                "_blank"
              )
            }
            startIcon={<Download />}
          >
            {t("l.btn.download.receipt.to.sign")}
          </ButtonSpg>
        </GridItem>
        <GridItem sm={12} md={12}>
          <AlertSpg severity={"info"}>{t("m.esign.instructions.step2")}</AlertSpg>
        </GridItem>
        <GridItem sm={12} md={12}>
          <AlertSpg severity={"info"}>{t("m.esign.instructions.step3")}</AlertSpg>
        </GridItem>
      </GridContainer>
      <FormProvider {...methods}>
        <GridContainer spacing={5} mt={0}>
          <GridItem sm={12} md={12}>
            {shouldShowFieldError("file.fileId", methods.formState, methods.getFieldState) ||
            getFieldError("file", methods.getFieldState) ? (
              <GridItem sm={12} md={12}>
                <Alert severity={"error"}>
                  {shouldShowFieldError("file.fileId", methods.formState, methods.getFieldState) ? (
                    <BoxSpg>{getFieldError("file.fileId", methods.getFieldState)}</BoxSpg>
                  ) : null}
                  {getFieldError("file", methods.getFieldState) ? (
                    <BoxSpg>{getFieldError("file", methods.getFieldState)}</BoxSpg>
                  ) : null}
                </Alert>
              </GridItem>
            ) : null}
          </GridItem>
          <GridItem xs={6} sm={4} md={3}>
            <FileUploadButton
              onFileChange={onFileChange}
              id="file-upload-signed-application"
              accept={"application/pdf"}
            />
            <FileDetails />
          </GridItem>
          <GridItem sm={12} md={12}>
            <Button
              color="primary"
              variant="contained"
              type="submit"
              onClick={methods.handleSubmit(
                (values) => onSubmit(values),
                (errors) => console.log(errors)
              )}
            >
              {t("l.btn.file.signed")}
            </Button>
            <ButtonSpg ml={5} color="error" variant="contained" onClick={() => setUnsignedDialogOpen(true)}>
              {t("l.btn.file.unsigned")}
            </ButtonSpg>
          </GridItem>
        </GridContainer>
      </FormProvider>
      <SimpleConfirmDialog
        dialogTitleText={"t.confirm.file.unsigned"}
        open={unsignedDialogOpen}
        setOpen={setUnsignedDialogOpen}
        alertText={"m.confirm.file.unsigned"}
        alertType={"warning"}
        onConfirm={fileUnsignedApplication}
      />
    </BoxSpg>
  );
};
export default ApplicationSignForm;
