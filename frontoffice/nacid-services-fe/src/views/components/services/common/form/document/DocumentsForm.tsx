import {
  AsyncCallArgs,
  BoxSpg,
  DividerSpg,
  getFieldError,
  GridContainer,
  GridItem,
  shouldShowFieldError,
  useAsyncCall,
} from "@duosoftbg/nacid-components";
import DocumentsFormSection from "./DocumentsFormSection";
import { Alert, Button, Typography } from "@mui/material";
import React, { useEffect, useState } from "react";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";
import { useTranslation } from "react-i18next";
import { getAppDocTypes } from "../../../../../../services/docTypeCalls";
import AttachmentsFormList from "./AttachmentsFormList";
import { FormProvider } from "react-hook-form";

const DocumentsForm = ({
  methods,
  setDocumentsFormEdited,
  onSubmit,
  hasAttachmentForm,
  hasAttachmentType,
  informingMessageCode = undefined,
  fileGroup = undefined,
  applicationId,
}) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const { asyncCall } = useAsyncCall();
  const [docTypes, setDocTypes] = useState([]);

  const attachments = methods.watch("attachments", []);

  useEffect(() => {
    dispatch(setDocumentsFormEdited(methods.formState.isDirty));
  }, [dispatch, methods.formState.isDirty, setDocumentsFormEdited]);

  useEffect(() => {
    if (hasAttachmentType) {
      const fetchDocTypes: AsyncCallArgs = {
        withGlobalBackdrop: false,
        promise: getAppDocTypes(applicationId),
        processResponseErrors: false,
        onSuccess: (response) => {
          setDocTypes(response.data);
        },
        onError: (errResponse) => {
          setDocTypes([]);
        },
      };
      asyncCall(fetchDocTypes);
    }
  }, [asyncCall, hasAttachmentType, applicationId]);

  const handleAddAttachment = (attachment) => {
    const newArray = [...methods.getValues().attachments, attachment];
    methods.setValue("attachments", newArray, { shouldDirty: true });
  };

  const handleRemoveAttachment = (index) => {
    methods.setValue(`attachments.${index}.forRemoval`, true, { shouldDirty: true });
    dispatch(setDocumentsFormEdited(true));
  };

  return (
    <BoxSpg>
      <DocumentsFormSection
        docTypes={docTypes}
        onAddAttachment={handleAddAttachment}
        hasAttachmentForm={hasAttachmentForm}
        hasAttachmentType={hasAttachmentType}
        informingMessageCode={informingMessageCode}
        fileGroup={fileGroup}
      />
      {shouldShowFieldError("attachments", methods.formState, methods.getFieldState) ? (
        <GridContainer>
          <GridItem sm={12} md={12} pr={0}>
            <Alert severity={"error"}>{getFieldError("attachments", methods.getFieldState)}</Alert>
          </GridItem>
        </GridContainer>
      ) : null}

      <FormProvider {...methods}>
        <form
          onSubmit={methods.handleSubmit(onSubmit, (errors) => {
            console.log(errors);
          })}
        >
          <AttachmentsFormList
            docTypes={docTypes}
            attachments={attachments}
            onAttachmentRemove={(index) => handleRemoveAttachment(index)}
            hasAttachmentForm={hasAttachmentForm}
            hasAttachmentType={hasAttachmentType}
          />
          <DividerSpg my={4} />
          <BoxSpg>
            <Typography align={"left"}>
              <Button type={"submit"} variant={"contained"}>
                {t("l.btn.saveData")}
              </Button>
            </Typography>
          </BoxSpg>
        </form>
      </FormProvider>
    </BoxSpg>
  );
};
export default DocumentsForm;
