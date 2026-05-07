import { Helmet, HelmetProvider } from "react-helmet-async";
import { useRoutes } from "react-router-dom";
import routes from "./routes/routes";
import { useTranslation } from "react-i18next";
import { AppThemeProvider, ToastProvider } from "@duosoftbg/nacid-components";
import React from "react";

const App = () => {
  const content = useRoutes(routes);
  const { t } = useTranslation();

  return (
    <HelmetProvider>
      <Helmet defaultTitle={t("t.app.defaultTitle")} titleTemplate={t("t.app.titleTemplate")} title={t("t.services")} />
      <AppThemeProvider>
        <ToastProvider />
        {content}
      </AppThemeProvider>
    </HelmetProvider>
  );
};

export default App;
