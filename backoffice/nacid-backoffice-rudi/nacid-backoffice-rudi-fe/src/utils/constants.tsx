import { useTranslation } from "react-i18next";
import { Box } from "@mui/material";
import React from "react";
import { AlertSpg } from "@duosoftbg/nacid-components";

export const YesMessage = () => {
  const { t } = useTranslation();
  return <Box style={{ color: "green" }}>{t("m.yes")}</Box>;
};

export const NoMessage = () => {
  const { t } = useTranslation();
  return <Box style={{ color: "red" }}>{t("m.no")}</Box>;
};

export const RudiApplication = {
  rudiApplicationType: "AR",
  rudiSARApplication: "SAR",
  rudiUDIApplicationSybType: "UDI",
  rudiSARApplicationSybType: "SAR",
  rudiDOCApplicationSybType: "DOC",
};

export const CommissionCalendarConst = {
  applicantInfoStatus: "POS",
  calendarAppsDefaultDocflowStatus: "POS",
  statusFinished: "COD",
};

export const CalendarApplicationsSortFields = [
  { id: "entryNum", label: "l.entryNum" },
  { id: "entryDate", label: "l.table.head.entryDate" },
  { id: "applicantName", label: "l.table.head.applicantName" },
  { id: "universityName", label: "l.table.head.foreign.universityName" },
  { id: "universityCountryName", label: "l.table.head.universityCountryName" },
  { id: "recognizedEduLevelName", label: "l.table.head.recognizedEduLevel" },
  { id: "recognizedSpecialityName", label: "l.table.head.recognizedSpecialityName" },
  { id: "apnStatusName", label: "l.table.head.apnStatusName" },
];

export const JoinType = {
  joinAny: "ANY",
  joinAll: "ALL",
  joinOnlyAll: "ONLY_ALL",
};

export const MinioRelativePath = {
  STATEMENTS: "statements",
};
export const MinioRootDirectory = "rudi";

export const CalendarTemplates = {
  PROTOCOL_TEMPLATE: "rudi/protocol.docx",
  XLSX_REPORT_TEMPLATE: "rudi/commission_calendar_report.xlsx",
  MEMBERS_TEMPLATE: "rudi/commission_participation_list.docx",
};

// TODO: NACIDSE-16
export const ProcessEnvironments = {
  Api: {
    GradingScale: {
      GradingScaleApiUrl: process.env.REACT_APP_GRADING_SCALE_API_URL,
    },
  },
};

export const ErrorMessages = ({ errors }) => {
  const { t } = useTranslation();

  if (!errors || errors.length === 0) return null;

  return (
    <>
      {errors.map((error) => (
        <AlertSpg key={error.pointer || error.message} style={{ marginTop: "6px" }} severity="error">
          {error.params ? t(error.message, error.params) : t(error.message)}
        </AlertSpg>
      ))}
    </>
  );
};
