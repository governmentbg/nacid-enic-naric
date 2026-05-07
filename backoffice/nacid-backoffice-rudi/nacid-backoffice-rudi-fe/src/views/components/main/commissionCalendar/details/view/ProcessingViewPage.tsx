import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../common/layout/PageWrapper";
import {
  AlertSpg,
  AsyncCallArgs,
  BlockText,
  CardSpg,
  GridContainer,
  GridItem,
  isArrayNotEmpty,
  StringArrayChipList,
  useAsyncCall,
  ViewSection,
} from "@duosoftbg/nacid-components";
import CardContent from "@mui/material/CardContent";
import { getCalendarProcessData } from "../../../../../../axios/api/services";
import { useParams } from "react-router-dom";

const ProcessingViewPage = () => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();

  const params = useParams();
  const calendarId = params.calendarId;
  const applicationId = params.applicationId;

  const [processingData, setProcessingData] = useState(null);
  const [error, setError] = useState(false);

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getCalendarProcessData(calendarId, applicationId),
      withGlobalBackdrop: true,
      onSuccess: (response) => {
        setProcessingData(response);
        setError(false);
      },
      onError: () => {
        setError(true);
      },
    };
    asyncCall(asyncCallArgs);

    // eslint-disable-next-line
  }, [calendarId,applicationId]);

  if (error) {
    return (
      <PageWrapper title={t("m.error")}>
        <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>
      </PageWrapper>
    );
  }

  return (
    <PageWrapper title={t("t.commission.calendar.processing.view")}>
      {processingData && (
        <>
          <CardSpg my={4} style={{ overflow: "visible" }}>
            <CardContent style={{ padding: 24, position: "relative" }}>
              <ViewSection label={"t.common.data"}>
                <GridContainer spacing={3} mt={0}>
                  {processingData?.motives && (
                    <GridItem sm={12} md={12}>
                      <BlockText label={"l.motives"} text={processingData.motives} />
                    </GridItem>
                  )}
                  {processingData?.applicantInfo && (
                    <GridItem sm={12} md={12}>
                      <BlockText label={"l.applicantInfo"} text={processingData.applicantInfo} />
                    </GridItem>
                  )}
                </GridContainer>

                <GridContainer spacing={3} mt={0}>
                  {processingData?.recognizedEduLevelName && (
                    <GridItem sm={6} md={6}>
                      <BlockText label={"l.recognized.educationLevel"} text={processingData.recognizedEduLevelName} />
                    </GridItem>
                  )}
                  {processingData?.recognizedQualification && (
                    <GridItem sm={6} md={6}>
                      <BlockText
                        label={"l.recognized.recognizedQualification"}
                        text={processingData.recognizedQualification}
                      />
                    </GridItem>
                  )}
                  {processingData?.recognizedProfGroupName && (
                    <GridItem sm={6} md={6}>
                      <BlockText label={"l.recognized.profGroup"} text={processingData.recognizedProfGroupName} />
                    </GridItem>
                  )}
                  {processingData?.statusName && (
                    <GridItem sm={6} md={6}>
                      <BlockText label={"l.nomenclature.status"} text={processingData.statusName} />
                    </GridItem>
                  )}
                </GridContainer>
              </ViewSection>

              {isArrayNotEmpty(processingData?.specialities) && (
                <ViewSection label={"t.specialities.data"}>
                  <div style={{ marginTop: "10px" }}>
                    <StringArrayChipList
                      list={processingData?.specialities}
                      listLabel={"l.selected.speciality"}
                      isLabelBold={true}
                      hasRemoval={false}
                    ></StringArrayChipList>
                  </div>
                </ViewSection>
              )}
            </CardContent>
          </CardSpg>
        </>
      )}
    </PageWrapper>
  );
};

export default ProcessingViewPage;
