import { Button, Dialog, DialogActions, DialogContent, DialogTitle } from "@mui/material";
import { FormProvider } from "react-hook-form";
import {
  SelectFormField,
  FileUploadButton,
  GridContainer,
  GridItem,
  useAsyncCall,
  AsyncCallArgs,
  useReactHookForm,
  AlertSpg,
} from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import { AttachedDocument } from "../../../../../../types/common/documentTypes";
import { initialAttachedDocument } from "../../../../../../init/common/documentInitialValues";
import FileDetails from "./FileDetails";
import { useEffect } from "react";
import { uploadFile } from "../../../../../../services/coreServicesCalls";
import { useGoogleReCaptcha } from "react-google-recaptcha-v3";
import { createAttachedDocumentValidationSchema } from "../../../../../../yup/common/document/documentValidationSchemas";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { copyTypeThunk } from "../../../../../../store/redux/slice/AppData/copyType";
import AddAttachmentDescriptionFormField from "./parts/AddAttachmentDescriptionFormField";
import { fileGroupConfig } from "../../../../../../config/fileGroupConfig";
import FileErrorAlert from "./FileErrorAlert";
import DocumentTypeAutocompleteField from "./DocumentTypeAutocompleteField";

const AddAttachmentDialog = ({
  open,
  onCloseDialog,
  onAddAttachment,
  hasAttachmentForm,
  hasAttachmentType,
  fileGroup = null,
  docTypes,
}) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();

  const { executeRecaptcha } = useGoogleReCaptcha();

  const dispatch = useAppDispatch();

  const thunkStateCopyType = useAppSelector((state) => {
    return state.AppData.CopyType;
  });

  const { methods } = useReactHookForm<AttachedDocument>({
    defaultValues: initialAttachedDocument,
    validationSchema: createAttachedDocumentValidationSchema,
  });

  useEffect(() => {
    if (hasAttachmentForm) {
      dispatch(copyTypeThunk());
    }
  }, [dispatch, hasAttachmentForm]);

  useEffect(() => {
    methods.reset(initialAttachedDocument);
  }, [open, methods]);

  const onFileChange = async (e) => {
    const token = await executeRecaptcha("ServicesFileUpload");
    const uploadAttachmentFile: AsyncCallArgs = {
      promise: uploadFile(e.target.files[0], token, fileGroup),
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

  const onSubmitDialog = (e) => {
    methods.handleSubmit(
      (values) => onAddAttachment(values),
      (errors) => {
        console.log(errors);
      }
    )();
  };

  return (
    <Dialog open={open} onClose={onCloseDialog}>
      <DialogTitle>{t("t.attachment.dialog.title")}</DialogTitle>
      <DialogContent>
        <FormProvider {...methods}>
          <GridContainer spacing={5} mt={0}>
            <FileErrorAlert methods={methods} />
            {hasAttachmentType && docTypes && docTypes.length > 0 && (
              <GridItem sm={12} md={12}>
                <AlertSpg severity={"info"}>{t("m.attachment.add.info")}</AlertSpg>
              </GridItem>
            )}
            {hasAttachmentType && docTypes && docTypes.length > 0 && (
              <DocumentTypeAutocompleteField docTypes={docTypes} methods={methods} />
            )}
            {hasAttachmentForm && (
              <GridItem sm={12} md={12}>
                <SelectFormField
                  fieldName={"attachmentForm.id"}
                  labelCode={"l.attachment.attachmentForm"}
                  addEmptyOption={true}
                  selectOptions={thunkStateCopyType.data.map((option) => {
                    return { value: option.id, text: option.name, active: option.isActive };
                  })}
                />
              </GridItem>
            )}
            <AddAttachmentDescriptionFormField />
            <GridItem sm={12} md={12}>
              <FileUploadButton
                onFileChange={onFileChange}
                id="file-upload-modal-file"
                accept={fileGroup != null ? fileGroupConfig[fileGroup] : fileGroupConfig.general}
              />
              <FileDetails />
            </GridItem>
          </GridContainer>
        </FormProvider>
      </DialogContent>
      <DialogActions>
        <Button color="primary" variant="contained" type="submit" onClick={onSubmitDialog}>
          {t("l.btn.save")}
        </Button>
        <Button variant="outlined" onClick={onCloseDialog} sx={{ ml: 3 }}>
          {t("l.btn.cancel")}
        </Button>
      </DialogActions>
    </Dialog>
  );
};
export default AddAttachmentDialog;
