import { CardSpg } from "@duosoftbg/nacid-components";
import CardContent from "@mui/material/CardContent";
import * as React from "react";
import CommonInformationViewSection from "./details/view/CommonInformationViewSection";
import ApplicationsViewSection from "./details/view/ApplicationsViewSection";
import MembersViewSection from "./details/view/MembersViewSection";

const CommissionCalendarView = () => {
  return (
    <CardSpg my={4} style={{ overflow: "visible" }}>
      <CardContent style={{ padding: 24, position: "relative" }}>
        <CommonInformationViewSection></CommonInformationViewSection>
        <ApplicationsViewSection></ApplicationsViewSection>
        <MembersViewSection></MembersViewSection>
      </CardContent>
    </CardSpg>
  );
};

export default CommissionCalendarView;
