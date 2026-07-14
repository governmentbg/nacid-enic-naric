import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { useParams } from "react-router-dom";
import PageWrapper from "../../../components/common/layout/PageWrapper";
import { AsyncCallArgs, useAsyncCall, CircularLoader, AlertSpg } from "@duosoftbg/nacid-components";
import { checkIfRudiApplicationExists } from "../../../../axios/api/services";
import { AppType } from "@duosoftbg/nacid-backoffice-components";
import UdirecAppEdit from "../../../components/main/applications/udirec/edit/UdirecAppEdit";
import SARAppEdit from "../../../components/main/applications/sar/edit/SARAppEdit";
import DocrecAppEdit from "../../../components/main/applications/docrec/edit/DocrecAppEdit";

type AppEditPageProps = {
  appType: AppType;
};

const getTitle = (appType: AppType) => {
  switch (appType) {
    case AppType.DOCREC_APPLICATION:
      return "t.docDegreeRec.applications.edit";
    case AppType.UDIREC_APPLICATION:
      return "t.diplomaRec.applications.edit";
    case AppType.SAR_APPLICATION:
      return "t.sar.applications.edit";
  }
};

const AppEditPage = ({ appType }: AppEditPageProps) => {
  const { t } = useTranslation();
  const { id } = useParams();
  const { asyncCall } = useAsyncCall();
  const [loading, setLoading] = useState(true);
  const [exists, setExists] = useState(false);
  const pageTitle = getTitle(appType);

  useEffect(() => {
    if (id) {
      const asyncCallArgs: AsyncCallArgs = {
        promise: checkIfRudiApplicationExists(id, appType),
        processResponseErrors: false,
        onSuccess: () => {
          setExists(true);
          setLoading(false);
        },
        onError: () => {
          setExists(false);
          setLoading(false);
        },
      };
      asyncCall(asyncCallArgs);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [id]);

  if (loading) {
    return (
      <PageWrapper title={t(pageTitle)} hidePageTitle={true}>
        <CircularLoader />
      </PageWrapper>
    );
  }

  if (!exists) {
    return (
      <PageWrapper title={t(pageTitle)} hidePageTitle={true}>
        <AlertSpg severity="error">{t("m.application.notFound")}</AlertSpg>
      </PageWrapper>
    );
  }

  return (
    <PageWrapper title={t(pageTitle)} hidePageTitle={true}>
      <Content appType={appType} id={id} />
    </PageWrapper>
  );
};

const Content = ({ appType, id }: { appType: AppType; id: string }) => {
  switch (appType) {
    case AppType.UDIREC_APPLICATION: {
      return <UdirecAppEdit id={id} />;
    }
    case AppType.DOCREC_APPLICATION: {
      return <DocrecAppEdit id={id} />;
    }
    case AppType.SAR_APPLICATION: {
      return <SARAppEdit id={id} />;
    }
  }
};

export default AppEditPage;
