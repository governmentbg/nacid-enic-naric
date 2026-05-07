import { useTranslation } from "react-i18next";
import PageWrapper from "../../components/common/layout/PageWrapper";
import React from "react";
import CommonReport from "../../components/main/report/common/CommonReport";
import { CardSpg } from "@duosoftbg/nacid-components";
import { CardContent } from "@mui/material";

const CommonReportPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.common.report")}>
      <CardSpg my={4} style={{ overflow: "visible" }}>
        <CardContent style={{ position: "relative" }}>
          <CommonReport />
        </CardContent>
      </CardSpg>
    </PageWrapper>
  );
};
export default CommonReportPage;
