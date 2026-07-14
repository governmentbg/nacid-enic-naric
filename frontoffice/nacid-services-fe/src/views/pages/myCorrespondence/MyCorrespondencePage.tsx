import { useTranslation } from "react-i18next";
import PageWrapper from "../../components/common/layout/PageWrapper";
import { BorderGreyBox, BoxSpg, CardSpg } from "@duosoftbg/nacid-components";
import { CardContent } from "@mui/material";
import React from "react";
import MyCorrespondenceFilterForm from "../../components/services/myCorrespondence/MyCorrespondenceFilterForm";
import MyCorrespondenceList from "../../components/services/myCorrespondence/MyCorrespondenceList";

const MyCorrespondencePage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.page.my.correspondence")}>
      <CardSpg my={4} style={{ overflow: "visible" }}>
        <CardContent style={{ position: "relative" }}>
          <BoxSpg>
            <BorderGreyBox>
              <MyCorrespondenceFilterForm />
            </BorderGreyBox>
            <MyCorrespondenceList />
          </BoxSpg>
        </CardContent>
      </CardSpg>
    </PageWrapper>
  );
};
export default MyCorrespondencePage;
