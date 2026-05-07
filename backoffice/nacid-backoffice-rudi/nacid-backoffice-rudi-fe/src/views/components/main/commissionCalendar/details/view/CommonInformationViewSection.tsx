import {
  AlertSpg,
  AsyncCallArgs,
  BlockText,
  CircularLoader,
  GridContainer,
  GridItem,
  useAsyncCall,
  ViewSection,
} from "@duosoftbg/nacid-components";
import * as React from "react";
import { useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { useEffect, useState } from "react";
import { selectCommissionCalendar } from "../../../../../../axios/api/services";

const CommonInformationViewSection = () => {
  const calendarId = useParams().calendarId;
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const [calendar, setCalendar] = useState(null);
  const [error, setError] = useState(false);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: selectCommissionCalendar(calendarId),
      onSuccess: (response) => {
        setCalendar(response);
        setError(false);
        setLoading(false);
      },
      onError: () => {
        setError(true);
        setLoading(false);
      },
    };
    asyncCall(asyncCallArgs);

    // eslint-disable-next-line
    }, [calendarId]);

  if (loading) {
    return (
      <ViewSection label={"t.commonInformation"}>
        <div style={{ marginTop: "10px" }}>
          <CircularLoader></CircularLoader>
        </div>
      </ViewSection>
    );
  }

  if (error) {
    return (
      <ViewSection label={"t.commonInformation"}>
        <GridContainer spacing={3} mt={2}>
          <AlertSpg style={{ width: "100%" }} severity="error">
            {t("m.error.serverFetchingError")}
          </AlertSpg>
        </GridContainer>
      </ViewSection>
    );
  }

  return (
    <ViewSection label={"t.commonInformation"}>
      <GridContainer spacing={3} mt={0}>
        {calendar?.sessionNum && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.sessionNum"} text={calendar.sessionNum} />
          </GridItem>
        )}
        {calendar?.sessionTime && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.sessionTime"} text={calendar.sessionTime} />
          </GridItem>
        )}
        {calendar?.status && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.commissionStatus"} text={calendar.status.name} />
          </GridItem>
        )}
        {calendar?.notes && (
          <GridItem sm={12} md={12}>
            <BlockText label={"l.notes"} text={calendar.notes} />
          </GridItem>
        )}
      </GridContainer>
    </ViewSection>
  );
};

export default CommonInformationViewSection;
