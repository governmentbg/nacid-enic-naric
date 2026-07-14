import * as React from "react";
import {
  AlertSpg,
  AsyncCallArgs,
  CardSpg,
  DividerSpg,
  GridContainer,
  GridItem,
  InitialValues as InitialValuesBase,
  isEmpty,
  TextareaFormField,
  useAsyncCall,
  useReactHookForm,
  useScrollOnValidationError,
} from "@duosoftbg/nacid-components";
import { useNavigate } from "react-router-dom";
import { useTranslation } from "react-i18next";
import CardContent from "@mui/material/CardContent";
import { useEffect, useState } from "react";
import { FormProvider } from "react-hook-form";
import { Button, Typography } from "@mui/material";
import CommissionMembersDocTypesSelectField from "../CommissionMembersDocTypesSelectField";
import ExpertsSelectField from "../ExpertsSelectField";
import { ApplicationCommissionMemberStatementDetails } from "../../../../../../../../../../../../../types/applications/common/commissionMemberStatements/applicationCommissionMemberStatementTypes";
import { applicationCommissionMemberStatementInitialValues } from "../../../../../../../../../../../../../init/application/commissionMemberStatements/applicationCommissionMemberStatementInitialValues";
import {
  getApplicationCommissionMemberStatement,
  saveApplicationCommissionMemberStatement,
} from "../../../../../../../../../../../../../axios/api/services";
import PageWrapper from "../../../../../../../../../../../common/layout/PageWrapper";
import { vCommissionMemberStatementSchema } from "../../../../../../../../../../../../../yup/schema/applications/common/schemas";
import {
  AppType,
  AttachmentListEdit,
  DocumentCategories,
  FilesField,
  NavigationUtils,
} from "@duosoftbg/nacid-backoffice-components";
import { MinioRootDirectory } from "../../../../../../../../../../../../../utils/constants";
import { toast } from "react-toastify";

type StatementEditProps = {
  appType: AppType;
  applicationId: number | string;
  statementId?: number | string;
};

const StatementEdit = ({ applicationId, statementId = null, appType }: StatementEditProps) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const [error, setError] = useState(false);
  const navigate = useNavigate();

  const { methods, handleSubmit } = useReactHookForm<ApplicationCommissionMemberStatementDetails>({
    defaultValues: applicationCommissionMemberStatementInitialValues,
    validationSchema: vCommissionMemberStatementSchema,
  });

  useScrollOnValidationError({ methods });

  useEffect(() => {
    if (statementId) {
      const asyncCallArgs: AsyncCallArgs = {
        promise: getApplicationCommissionMemberStatement(statementId),
        withGlobalBackdrop: true,
        onSuccess: (response) => {
          methods.reset({
            ...response,
            file: InitialValuesBase.forms.fileInitialValues,
          });
          setError(false);
        },
        onError: () => {
          methods.reset(applicationCommissionMemberStatementInitialValues);
          setError(true);
        },
      };
      asyncCall(asyncCallArgs);
    } else {
      methods.reset(applicationCommissionMemberStatementInitialValues);
      setError(false);
    }
    // eslint-disable-next-line
    }, [statementId, applicationId]);

  const onSubmit = () => {
    const asyncCreation: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: saveApplicationCommissionMemberStatement(applicationId, methods.getValues()),
      processResponseErrors: true,
      reactHooksForm: { methods },
      onSuccess: () => {
        navigate(NavigationUtils.editApplication(appType, applicationId));
        toast.success(t("m.create.success"));
      },
    };
    asyncCall(asyncCreation);
  };

  if (error) {
    return (
      <PageWrapper title={t("m.error")}>
        <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>
      </PageWrapper>
    );
  }
  return (
    <PageWrapper
      title={statementId ? t("t.application.expert.statement.edit") : t("t.application.expert.statement.add")}
    >
      <CardSpg my={4} style={{ overflow: "visible" }}>
        <CardContent style={{ padding: 24, position: "relative" }}>
          <FormProvider {...methods}>
            <form onSubmit={handleSubmit(onSubmit)}>
              <GridContainer spacing={3} mt={0}>
                <GridItem sm={6} md={6}>
                  <ExpertsSelectField field={"commissionMember.id"} isCreate={isEmpty(statementId)} required={true} />
                </GridItem>
                <GridItem sm={6} md={6}>
                  <CommissionMembersDocTypesSelectField field={"attachedDoc.documentType.id"} required={true} />
                </GridItem>
                <GridItem sm={12} md={12}>
                  <TextareaFormField rows={10} fieldName={"attachedDoc.description"} labelCode={"l.description"} />
                </GridItem>

                <FilesField
                  field={"attachedDoc.attachedDocAttachments"}
                  uploadFileProps={{
                    rootDirectory: MinioRootDirectory,
                    applicationId: applicationId,
                    activeUploadFileButton: true,
                    fileGroup: "doc",
                    accept:
                      "application/pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                    hint: "m.file.doc.allowed.type.message",
                    uploadBtnId: `file-upload-modal-file`,
                  }}
                  generateFileProps={{
                    rootDirectory: MinioRootDirectory,
                    activeGenerateFileButton: true,
                    docTypeField: "attachedDoc.documentType.id",
                    commissionMemberField: "commissionMember.id",
                    docCategory: DocumentCategories.COMMISSION_EXPERTS,
                    applicationId: applicationId,
                    mandatoryCommissionMember: true,
                  }}
                />
                <AttachmentListEdit field={"attachedDoc.attachedDocAttachments"} />
              </GridContainer>
              <DividerSpg my={4} />
              <GridContainer spacing={3}>
                <GridItem sm={12} md={12}>
                  <Typography align={"right"}>
                    <Button type={"submit"} variant={"contained"}>
                      {t("l.btn.save")}
                    </Button>
                  </Typography>
                </GridItem>
              </GridContainer>
            </form>
          </FormProvider>
        </CardContent>
      </CardSpg>
    </PageWrapper>
  );
};

export default StatementEdit;
