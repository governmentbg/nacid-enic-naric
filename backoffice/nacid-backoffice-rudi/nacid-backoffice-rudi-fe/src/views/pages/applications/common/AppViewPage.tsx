import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { useParams } from "react-router-dom";
import PageWrapper from "../../../components/common/layout/PageWrapper";
import { AlertSpg, AsyncCallArgs, CircularLoader, useAsyncCall } from "@duosoftbg/nacid-components";
import { checkIfRudiApplicationExists } from "../../../../axios/api/services";
import UdirecAppView from "../../../components/main/applications/udirec/view/UdirecAppView";
import { AppType } from "@duosoftbg/nacid-backoffice-components";
import DocrecAppView from "../../../components/main/applications/docrec/view/DocrecAppView";
import SARAppView from "../../../components/main/applications/sar/view/SARAppView";

type AppViewPageProps = {
  appType: AppType;
};

const getTitle = (appType: AppType) => {
  switch (appType) {
    case AppType.DOCREC_APPLICATION:
      return "t.docDegreeRec.applications.view";
    case AppType.UDIREC_APPLICATION:
      return "t.diplomaRec.applications.view";
    case AppType.SAR_APPLICATION:
      return "t.sar.applications.view";
  }
};

const AppViewPage = ({ appType }: AppViewPageProps) => {
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
      <Content appType={appType} />
    </PageWrapper>
  );
};

const Content = ({ appType }: { appType: AppType }) => {
  switch (appType) {
    case AppType.UDIREC_APPLICATION: {
      return <UdirecAppView />;
    }
    case AppType.DOCREC_APPLICATION: {
      return <DocrecAppView />;
    }
    case AppType.SAR_APPLICATION: {
      return <SARAppView />;
    }
  }
};

export default AppViewPage;
