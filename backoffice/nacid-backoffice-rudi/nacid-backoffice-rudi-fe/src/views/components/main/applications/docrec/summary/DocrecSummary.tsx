import { Grid } from "@mui/material";
import React from "react";
import { useTranslation } from "react-i18next";
import {
  AppType,
  SummaryReceiptButton,
  SummaryWrapper,
  TextBlock,
  useSummary,
} from "@duosoftbg/nacid-backoffice-components";
import { selectApplicationSummary } from "../../../../../../axios/api/services";

const DocrecSummary = ({ id }) => {
  const { loading, error, summary } = useSummary({
    appType: AppType.DOCREC_APPLICATION,
    id: id,
    selectSummaryFn: selectApplicationSummary,
  });

  return (
    <SummaryWrapper loading={loading} error={error} summary={summary}>
      <Content summary={summary} />
    </SummaryWrapper>
  );
};

const Content = ({ summary }) => {
  const { t } = useTranslation();

  if (!summary) {
    return null;
  }

  return (
    <>
      <Grid item xs={12} sm={4}>
        <TextBlock
          openBlank
          href={summary.docflowDocumentUrl}
          label={"l.entryNumAndDate"}
          value={`${summary.entryNum ?? t("l.notHave")} / ${summary.entryDate ?? t("l.notHave")}`}
        />
      </Grid>
      <Grid item xs={12} sm={4}>
        <TextBlock label={"l.docflowStatus"} value={summary?.docflowStatus ?? t("l.notHave")} />
      </Grid>
      <Grid item xs={12} sm={4}>
        <TextBlock label={"l.status"} value={summary?.status ?? t("l.notHave")} />
      </Grid>
      <Grid item xs={12} sm={4}>
        <TextBlock label={"l.responsibleUser"} value={summary?.responsibleUser ?? t("l.notHave")} />
      </Grid>
      <Grid item xs={12} sm={4}>
        <TextBlock label={"l.applicant"} value={summary?.applicant ?? t("l.notHave")} />
      </Grid>
      <Grid item xs={12} sm={4}>
        <TextBlock label={"l.backofficeDate"} value={summary.backofficeDate} />
      </Grid>
      <SummaryReceiptButton summary={summary} />
    </>
  );
};

export default DocrecSummary;
