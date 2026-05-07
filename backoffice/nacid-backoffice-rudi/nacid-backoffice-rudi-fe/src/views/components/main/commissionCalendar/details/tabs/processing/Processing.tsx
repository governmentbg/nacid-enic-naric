import React from "react";

import DecisionsSection from "./sections/decisions/DecisionsSection";
import { BoxSpg } from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import CalendarAttachment from "./sections/calendarAttachment/CalendarAttachment";

const Processing = () => {
  const { t } = useTranslation();
  return (
    <>
      <BoxSpg my={5} textAlign={"center"}>
        {t("t.commission.calendar.processing.data")}
      </BoxSpg>
      <CalendarAttachment />
      <DecisionsSection />
    </>
  );
};

export default Processing;
