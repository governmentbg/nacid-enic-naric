import {
  AlertSpg,
  CircularLoader,
  GridContainer,
  isArrayEmpty,
  isArrayNotEmpty,
  ViewSection,
} from "@duosoftbg/nacid-components";
import * as React from "react";
import { useParams } from "react-router-dom";
import useApplicationIdsControl from "../../hooks/useApplicationIdsControl";
import { useTranslation } from "react-i18next";
import ApplicationsListTable from "../tabs/applications/ApplicationsListTable";

const ApplicationsViewSection = () => {
  const { t } = useTranslation();
  const calendarId = useParams().calendarId;
  const { applicationIds, error, loading } = useApplicationIdsControl({
    calendarId: calendarId,
  });

  if (loading) {
    return (
      <ViewSection label={"t.applications"}>
        <div style={{ marginTop: "10px" }}>
          <CircularLoader></CircularLoader>
        </div>
      </ViewSection>
    );
  }
  if (error) {
    return (
      <ViewSection label={"t.applications"}>
        <GridContainer spacing={3} mt={2}>
          <AlertSpg style={{ width: "100%" }} severity="error">
            {t("m.error.serverFetchingError")}
          </AlertSpg>
        </GridContainer>
      </ViewSection>
    );
  }
  return (
    <ViewSection label={"t.applications"}>
      <GridContainer spacing={3} mt={2}>
        {isArrayNotEmpty(applicationIds) && <ApplicationsListTable applicationIds={applicationIds} />}
        {isArrayEmpty(applicationIds) && (
          <AlertSpg style={{ width: "100%" }} severity="info">
            {t("m.empty.list")}
          </AlertSpg>
        )}
      </GridContainer>
    </ViewSection>
  );
};

export default ApplicationsViewSection;
