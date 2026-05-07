import * as React from "react";
import { useEffect, useState } from "react";
import {
  AlertSpg,
  AsyncCallArgs,
  BlockFormBackdrop,
  BoxSpg,
  CircularLoader,
  ConfirmSubmitDialog,
  DividerSpg,
  FormSection,
  GridContainer,
  GridItem,
  RadiosFormField,
  SubmitFormButton,
  useAsyncCall,
  useReactHookForm,
  useReloadWatcherReader,
  useReloadWatcherWriter,
  ValidationErrors,
} from "@duosoftbg/nacid-components";
import { initializeYup, ReloadWatcherObject, TextBlock } from "@duosoftbg/nacid-backoffice-components";
import {
  selectDocrecRasInfo,
  selectRasCertificatePublicFiles,
  transferApplicationToRAS,
} from "../../../../../../../../../../axios/api/services";
import { useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { ApplicationCommissionMemberDetails } from "../../../../../../../../../../types/applications/common/commissionMembers/applicationCommissionMemberTypes";
import { FormProvider } from "react-hook-form";
import * as yup from "yup";

const RasPart = () => {
  const { t } = useTranslation();
  const { id } = useParams();
  const { asyncCall } = useAsyncCall();
  const [error, setError] = useState(false);
  const [loading, setLoading] = useState(true);
  const [rasInfo, setRasInfo] = useState(null);
  const { reloadWatcher } = useReloadWatcherReader(ReloadWatcherObject.build("docrecRasData", "update"));

  useEffect(() => {
    if (id) {
      asyncCall({
        promise: selectDocrecRasInfo(id),
        processResponseErrors: false,
        onSuccess: (response) => {
          setRasInfo(response);
          setError(false);
          setLoading(false);
        },
        onError: () => {
          setRasInfo(null);
          setError(true);
          setLoading(false);
        },
      });
      return () => {
        setRasInfo(null);
        setError(false);
        setLoading(true);
      };
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [id, reloadWatcher]);

  if (loading) {
    return (
      <BoxSpg>
        <FormSection label={"t.appSubSections.ras.full"}>
          <BoxSpg mt={3}>
            <CircularLoader />
          </BoxSpg>
        </FormSection>
      </BoxSpg>
    );
  }

  if (error) {
    return (
      <BoxSpg>
        <FormSection label={"t.appSubSections.ras.full"}>
          <BoxSpg mt={3}>
            <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>
          </BoxSpg>
        </FormSection>
      </BoxSpg>
    );
  }

  return (
    <BoxSpg>
      <FormSection label={"t.appSubSections.ras.full"}>
        <GridContainer spacing={3} mt={0}>
          <GridItem sm={12} md={12}>
            <Content rasInfo={rasInfo} />
          </GridItem>
        </GridContainer>
      </FormSection>
    </BoxSpg>
  );
};

const Content = ({ rasInfo }) => {
  const externalSystemId = rasInfo?.externalSystemId;
  const externalLink = rasInfo?.externalLink;
  const id = rasInfo?.applicationId;
  const meetRequirements = rasInfo?.doesMeetTransferRequirements;

  if (externalSystemId) {
    return <AlreadyTransferred externalLink={externalLink} />;
  }

  if (meetRequirements !== true) {
    return <DoesNotMeetRequirements />;
  }

  return <NotTransferred id={id} />;
};

const AlreadyTransferred = ({ externalLink }) => {
  return (
    <>
      <BoxSpg>
        <AlertSpg severity="success">Заявлението е прехвърлено в регистъра !</AlertSpg>
      </BoxSpg>
      {externalLink && (
        <BoxSpg mt={4}>
          <TextBlock openBlank href={externalLink} label={"Линк към заявлението"} value={`Преглед`} />
        </BoxSpg>
      )}
    </>
  );
};
const vRasRegister = () => {
  initializeYup(yup);
  return yup.object({
    certPublicFileId: yup.number().required(),
  });
};

const NotTransferred = ({ id }) => {
  const { asyncCall } = useAsyncCall();
  const [files, setFiles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    asyncCall({
      promise: selectRasCertificatePublicFiles(id),
      processResponseErrors: false,
      onSuccess: (response) => {
        setFiles(response);
        setLoading(false);
        setError(false);
      },
      onError: () => {
        setError(true);
        setLoading(false);
      },
    });
    return () => {
      setFiles([]);
      setLoading(true);
      setError(false);
    };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  if (loading) {
    return (
      <BoxSpg>
        <BoxSpg>
          <CircularLoader />
        </BoxSpg>
      </BoxSpg>
    );
  }

  if (error || files.length < 1) {
    return (
      <BoxSpg>
        <BoxSpg mt={2}>
          <AlertSpg severity="info">Заявелнието все още не е прехвърлено в регистъра !</AlertSpg>
        </BoxSpg>
        <BoxSpg mt={2}>
          <AlertSpg severity="warning">
            {
              'Заявлението не може да бъде прехвърлено в РАС! Моля проверете съществуват ли публични файлове в документа за "Удостоверение" в деловодната система, както и дали всичко със самото удостоверение е наред! ! '
            }
          </AlertSpg>
        </BoxSpg>
      </BoxSpg>
    );
  }

  return <NotTransferredForm id={id} files={files} />;
};

const NotTransferredForm = ({ id, files }) => {
  const [confirmModalState, setConfirmModalState] = useState({ open: false, submitFn: null });
  const { updateReloadWatcher } = useReloadWatcherWriter();

  const { methods, handleSubmit } = useReactHookForm<ApplicationCommissionMemberDetails>({
    defaultValues: {},
    validationSchema: vRasRegister,
  });

  const onSubmit = (formData) => {
    const asyncCreation: AsyncCallArgs = {
      withGlobalBackdrop: true,
      reactHooksForm: { methods: methods },
      promise: transferApplicationToRAS(id, formData),
      commonErrorMessage: "Възникна проблем при прехвърляне на заявление !",
      processResponseErrors: true,
      onSuccess: () => {
        updateReloadWatcher(ReloadWatcherObject.build("docrecRasData", "update"));
      },
    };
    setConfirmModalState({ open: true, submitFn: asyncCreation });
  };

  return (
    <>
      <BoxSpg>
        <FormProvider {...methods}>
          <ValidationErrors />

          <BoxSpg mt={2}>
            <AlertSpg severity="info">Заявелнието все още не е прехвърлено в регистъра !</AlertSpg>
          </BoxSpg>

          <form onSubmit={handleSubmit(onSubmit)}>
            <BlockFormBackdrop />
            <GridContainer mt={0}>
              <GridItem sm={12} md={12} style={{ marginLeft: 5, marginBottom: 5, marginTop: 12 }}>
                <RadiosFormField
                  labelCode={"Удостоверение"}
                  fieldName={`certPublicFileId`}
                  isInline={false}
                  size={"small"}
                  radioOptions={files.map((file) => {
                    return { value: file.id, text: file.name };
                  })}
                />
              </GridItem>
            </GridContainer>
            <DividerSpg my={4} />
            <BoxSpg>
              <SubmitFormButton withLoader withLoadingText label={"Прехвърляне в регистъра"} color="primary" />
            </BoxSpg>
            <ConfirmSubmitDialog
              alertText={"Сигурни ли сте, че искате да прехвърлите заявлението в регистъра ?"}
              dialogTitleText={"Прехвърляне"}
              modalState={confirmModalState}
              setModalState={setConfirmModalState}
            />
          </form>
        </FormProvider>
      </BoxSpg>
    </>
  );
};

const DoesNotMeetRequirements = () => {
  const { t } = useTranslation();
  return (
    <>
      <BoxSpg>
        <AlertSpg severity="warning">{t("m.ras.does.not.match.requirements")}</AlertSpg>
      </BoxSpg>
      <BoxSpg mt={4} style={{ fontWeight: "bold", fontSize: 14 }}>
        Изисквания:
      </BoxSpg>
      <BoxSpg mt={1} style={{ fontSize: 13 }}>
        - Статус "Признато"
      </BoxSpg>
      <BoxSpg mt={1} style={{ fontSize: 13 }}>
        - Деловоден статус "Издадено"
      </BoxSpg>
      <BoxSpg mt={1} style={{ fontSize: 13 }}>
        - Прикачено удостоверение
      </BoxSpg>
    </>
  );
};

export default RasPart;
