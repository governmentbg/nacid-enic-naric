import { Helmet, HelmetProvider } from "react-helmet-async";
import { useRoutes } from "react-router-dom";
import routes from "./routes/routes";
import { useTranslation } from "react-i18next";
import { AppThemeProvider, Page403, SecurityGuard, SecurityRole, ToastProvider } from "@duosoftbg/nacid-components";
import React from "react";
import NoSidebarLayout from "./views/layouts/NoSidebarLayout";

const App = () => {
  const content = useRoutes(routes);
  const { t } = useTranslation();

  return (
    <HelmetProvider>
      <Helmet
        defaultTitle={t("t.nacidBackOfficeSystem")}
        titleTemplate={t("t.nacidBackOfficeSystemTitleTemplate")}
        title={t("t.page.rudi")}
      />
      <AppThemeProvider>
        <ToastProvider />
        <GlobalSecurityGuard>{content}</GlobalSecurityGuard>
      </AppThemeProvider>
    </HelmetProvider>
  );
};

const GlobalSecurityGuard = ({ children }) => {
  return (
    <SecurityGuard
      displayOnUnauthorized={
        <NoSidebarLayout>
          <Page403 withButton={false} />
        </NoSidebarLayout>
      }
      checkForRoles={[SecurityRole.RudiApplicationAccess]}
    >
      {children}
    </SecurityGuard>
  );
};

export default App;
