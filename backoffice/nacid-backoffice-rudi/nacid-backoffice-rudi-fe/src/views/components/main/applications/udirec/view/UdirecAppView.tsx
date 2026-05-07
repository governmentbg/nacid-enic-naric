import { AppType, ViewAttachmentsData } from "@duosoftbg/nacid-backoffice-components";
import { useParams } from "react-router-dom";
import {
  AppAccordionDetailsWrapper,
  BoxSpg,
  useViewDataControl,
  ViewContentWrapper,
} from "@duosoftbg/nacid-components";
import { selectRudiApplicationById } from "../../../../../../axios/api/services";
import ViewMainData from "../../common/sections/mainData/ViewMainData";
import ViewEducationData from "../../common/sections/educationData/ViewEducationData";
import ViewExpertsStatementsData from "../../common/tabs/closingData/ViewExpertsStatementsData";
import ViewStatusData from "../../common/sections/statusData/ViewStatusData";
import React from "react";
import UdirecSummary from "../summary/UdirecSummary";

const viewType = `${AppType.UDIREC_APPLICATION}-view`;

const UdirecAppView = () => {
  const { id } = useParams();

  useViewDataControl({
    viewType: viewType,
    viewId: id,
    serviceFn: selectRudiApplicationById(id),
  });

  return (
    <>
      <UdirecSummary id={id} />
      <BoxSpg>
        <ViewContentWrapper viewType={viewType} viewId={id} loaderType={"circular"}>
          <AppAccordionDetailsWrapper>
            <ViewMainData appType={AppType.UDIREC_APPLICATION} />
            <ViewEducationData appType={AppType.UDIREC_APPLICATION} />
            <ViewAttachmentsData />
            <ViewStatusData />
            <ViewExpertsStatementsData />
          </AppAccordionDetailsWrapper>
        </ViewContentWrapper>
      </BoxSpg>
    </>
  );
};

export default UdirecAppView;
