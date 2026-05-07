import { useParams } from "react-router-dom";
import {
  AppAccordionDetailsWrapper,
  BoxSpg,
  useViewDataControl,
  ViewContentWrapper,
} from "@duosoftbg/nacid-components";
import { selectRudiApplicationById } from "../../../../../../axios/api/services";
import ViewMainData from "../../common/sections/mainData/ViewMainData";
import { AppType, ViewAttachmentsData } from "@duosoftbg/nacid-backoffice-components";
import ViewEducationData from "../../common/sections/educationData/ViewEducationData";
import ViewExpertsStatementsData from "../../common/tabs/closingData/ViewExpertsStatementsData";
import ViewStatusData from "../../common/sections/statusData/ViewStatusData";
import React from "react";
import DocrecSummary from "../summary/DocrecSummary";

const viewType = `${AppType.DOCREC_APPLICATION}-view`;

const DocrecAppView = () => {
  const { id } = useParams();

  useViewDataControl({
    viewType: viewType,
    viewId: id,
    serviceFn: selectRudiApplicationById(id),
  });

  return (
    <>
      <DocrecSummary id={id} />
      <BoxSpg>
        <ViewContentWrapper viewType={viewType} viewId={id} loaderType={"circular"}>
          <AppAccordionDetailsWrapper>
            <ViewMainData appType={AppType.DOCREC_APPLICATION} />
            <ViewEducationData appType={AppType.DOCREC_APPLICATION} />
            <ViewAttachmentsData />
            <ViewStatusData />
            <ViewExpertsStatementsData />
          </AppAccordionDetailsWrapper>
        </ViewContentWrapper>
      </BoxSpg>
    </>
  );
};

export default DocrecAppView;
