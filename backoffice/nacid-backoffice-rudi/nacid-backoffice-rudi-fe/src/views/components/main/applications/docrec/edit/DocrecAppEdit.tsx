import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { AsyncCallArgs, CardSpg, DividerSpg, useAsyncCall } from "@duosoftbg/nacid-components";
import EducationData from "../edit/tabs/educationData/EducationData";
import MainData from "../edit/tabs/mainData/MainData";
import { useTranslation } from "react-i18next";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { Box, CardContent, Tab, Tabs } from "@mui/material";
import { AppType, AttachmentsData, CoreApiServicesBase, EFilledData } from "@duosoftbg/nacid-backoffice-components";
import StatusData from "../../common/tabs/statusData/StatusData";
import DocrecSummary from "../summary/DocrecSummary";
import useRudiFormDirtyStateProcessor from "../../../../../../store/redux/slice/ComponentsControl/useRudiFormDirtyStateProcessor";
import { FormDirtyStateProcessorFn } from "../../../../../../config/functions/formDirtyStateProcessorFn";
import ClosingData from "./tabs/closingData/ClosingData";

const TabDivider = styled(DividerSpg)`
  border-width: 1px;
  margin-top: -2px;
`;

function a11yProps(key) {
  return {
    id: `doc-degree-rec-modification-section-${key}`,
    "aria-controls": `doc-degree-rec-modification-section-tabpanel-${key}`,
  };
}

const tabsWithEFiling = [
  {
    key: 0,
    title: "t.efilledData",
    component: <EFilledData appType={AppType.DOCREC_APPLICATION} />,
  },
  {
    key: 1,
    title: "t.applicationData",
    component: <MainData />,
  },
  {
    key: 2,
    title: "t.educationData",
    component: <EducationData />,
  },
  {
    key: 3,
    title: "t.attachmentsData",
    component: <AttachmentsData appType={AppType.DOCREC_APPLICATION} />,
  },
  {
    key: 4,
    title: "t.statusData",
    component: <StatusData appType={AppType.DOCREC_APPLICATION} />,
  },
  {
    key: 5,
    title: "t.closingData",
    component: <ClosingData appType={AppType.DOCREC_APPLICATION} />,
  },
];

const tabsNoEFiling = [
  {
    key: 0,
    title: "t.applicationData",
    component: <MainData />,
  },
  {
    key: 1,
    title: "t.educationData",
    component: <EducationData />,
  },
  {
    key: 2,
    title: "t.attachmentsData",
    component: <AttachmentsData appType={AppType.DOCREC_APPLICATION} />,
  },
  {
    key: 3,
    title: "t.statusData",
    component: <StatusData appType={AppType.DOCREC_APPLICATION} />,
  },
  {
    key: 4,
    title: "t.closingData",
    component: <ClosingData appType={AppType.DOCREC_APPLICATION} />,
  },
];

const DocrecAppEdit = ({ id }: { id: string }) => {
  const { t } = useTranslation();
  const { activeTab } = useAppSelector((state) => {
    return state.ComponentsControl.docrecApplicationsControl.editPageTab;
  });

  const executeChangeTabFn = useRudiFormDirtyStateProcessor();
  const handleChange = (event, newValue) => {
    executeChangeTabFn({
      proceedFnKey: FormDirtyStateProcessorFn.handleTabChangeDocrec.name,
      proceedFnParams: { activeTab: newValue },
    });
  };

  const [tabs, setTabs] = useState(tabsNoEFiling);
  const { asyncCall } = useAsyncCall();

  useEffect(() => {
    if (id) {
      const asyncCallArgs: AsyncCallArgs = {
        promise: CoreApiServicesBase.checkIfFoAppExists(id),
        processResponseErrors: false,
        onSuccess: () => {
          setTabs(tabsWithEFiling);
        },
        onError: () => {
          setTabs(tabsNoEFiling);
          if (activeTab === 1) {
            executeChangeTabFn({
              proceedFnKey: FormDirtyStateProcessorFn.handleTabChangeDocrec.name,
              proceedFnParams: { activeTab: 0 },
            });
          }
        },
      };
      asyncCall(asyncCallArgs);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [id]);

  return (
    <>
      <DocrecSummary id={id} />
      <CardSpg my={4} style={{ overflow: "visible" }}>
        <CardContent style={{ position: "relative" }}>
          <Tabs style={{ marginTop: "-15px" }} variant="fullWidth" centered value={activeTab} onChange={handleChange}>
            {tabs.map((tab) => (
              <Tab key={tab.key} label={t(tab.title)} {...a11yProps(tab.key)} />
            ))}
          </Tabs>
          <Box>
            <TabDivider />
            {tabs.map((tab) => (
              <div
                key={tab.key}
                role="tabpanel"
                hidden={activeTab !== tab.key}
                id={`doc-degree-rec-modification-section-tabpanel-${tab.key}`}
                aria-labelledby={`doc-degree-rec-modification-section-tab-${tab.key}`}
              >
                {activeTab === tab.key && tab.component}
              </div>
            ))}
          </Box>
        </CardContent>
      </CardSpg>
    </>
  );
};

export default DocrecAppEdit;
