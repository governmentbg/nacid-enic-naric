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
import { ExpressServiceTypeLabel, ReferenceDataCode } from "@duosoftbg/nacid-components";

const SarSummary = ({ id }) => {
  const { loading, error, summary } = useSummary({
    appType: AppType.SAR_APPLICATION,
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

  const sarFlagColorCode = summary?.sarFlagColorCode;

  return (
    <>
      {summary?.serviceTypeCode !== ReferenceDataCode.SERVICE_TYPE_EXPRESS && (
        <Grid item xs={12} sm={4}>
          <TextBlock
            openBlank
            href={summary.docflowDocumentUrl}
            label={"l.entryNumAndDate"}
            value={`${summary.entryNum ?? t("l.notHave")} / ${summary.entryDate ?? t("l.notHave")}`}
          />
        </Grid>
      )}
      {summary?.serviceTypeCode === ReferenceDataCode.SERVICE_TYPE_EXPRESS && (
        <>
          <Grid item xs={12} sm={3.4}>
            <TextBlock
              openBlank
              href={summary.docflowDocumentUrl}
              label={"l.entryNumAndDate"}
              value={`${summary.entryNum ?? t("l.notHave")} / ${summary.entryDate ?? t("l.notHave")}`}
            />
          </Grid>
          <Grid item xs={12} sm={0.6} mt={0.4} style={{ paddingLeft: 0 }}>
            <ExpressServiceTypeLabel />
          </Grid>
        </>
      )}
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
      {sarFlagColorCode && (
        <Grid item xs={12} sm={4}>
          <TextBlock label={"l.sarFlag"}>
            {sarFlagColorCode?.statute?.name && (
              <>
                <span style={{ fontSize: 14, color: fillColor(sarFlagColorCode?.statute?.color) }}>
                  {sarFlagColorCode?.statute?.name}
                </span>{" "}
              </>
            )}
            {sarFlagColorCode?.authenticity?.name && (
              <>
                <span style={{ fontSize: 14, color: fillColor(sarFlagColorCode?.authenticity?.color) }}>
                  {sarFlagColorCode?.authenticity?.name}
                </span>{" "}
              </>
            )}
            {sarFlagColorCode?.recommendation?.name && (
              <>
                <span style={{ fontSize: 14, color: fillColor(sarFlagColorCode?.recommendation?.color) }}>
                  {sarFlagColorCode?.recommendation?.name}
                </span>
              </>
            )}
            {(sarFlagColorCode?.statute?.color === "RED" ||
              sarFlagColorCode?.authenticity?.color === "RED" ||
              sarFlagColorCode?.recommendation?.color === "RED") && (
              <div style={{ fontSize: 14, color: "#F00", marginTop: 5 }}>{t("m.sarApp.toEnd")}</div>
            )}
          </TextBlock>
        </Grid>
      )}
      <Grid item xs={12} sm={8}>
        <TextBlock label={"l.diplomaOwner"} value={summary?.diplomaOwner ?? t("l.notHave")} />
      </Grid>
      <SummaryReceiptButton summary={summary} />
    </>
  );
};

const fillColor = (colorCode: "RED" | "BLUE" | "GREY" | "GREEN") => {
  if (!colorCode) {
    return null;
  }

  switch (colorCode) {
    case "GREY": {
      return "#8a8a8a";
    }
    case "BLUE": {
      return "#080cea";
    }
    case "RED": {
      return "#F00";
    }
    case "GREEN": {
      return "#0aa600";
    }
  }
  return null;
};

export default SarSummary;
