import { TextSection, GridItem, GridContainer, THUNK_STATUS, BoxSpg, AlertSpg } from "@duosoftbg/nacid-components";
import React from "react";
import { useTranslation } from "react-i18next";
import DmsFileDownload from "./DmsFileDownload";

const DmsDetails = ({ dmsDetailsState, label }) => {
  const { t } = useTranslation();

  if (!dmsDetailsState) {
    return null;
  }
  if (dmsDetailsState.status === THUNK_STATUS.FULFILLED) {
    return (
      <TextSection label={label}>
        <GridContainer mt={0} spacing={4}>
          {dmsDetailsState.data.files.map((file) => (
            <GridItem sm={6} md={6} key={file.id}>
              <DmsFileDownload file={file} />
            </GridItem>
          ))}
        </GridContainer>
      </TextSection>
    );
  } else {
    return (
      <BoxSpg>
        <GridContainer spacing={4} mt={0}>
          <GridItem sm={12} md={12}>
            {dmsDetailsState.status === THUNK_STATUS.REJECTED && (
              <AlertSpg severity={"error"}>{t("m.generic.error.service.fail")}</AlertSpg>
            )}
          </GridItem>
        </GridContainer>
      </BoxSpg>
    );
  }
};
export default DmsDetails;
