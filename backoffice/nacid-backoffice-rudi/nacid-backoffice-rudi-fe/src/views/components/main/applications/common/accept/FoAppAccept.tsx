import * as React from "react";
import { Box, CardContent, Tab, Tabs } from "@mui/material";
import { BoxSpg, CardSpg, DividerSpg, ValidationErrors } from "@duosoftbg/nacid-components";
import {
  AddressDialogsProvider,
  AppType,
  PersonDialogsProvider,
  FoAppRevertStatusDialog,
  EFilledData,
  FoAppAcceptSubTitle,
  FoAcceptNotes,
  DeleteFoAcceptNoteDialog,
  SaveFoAcceptNoteDialog,
  FoAppDeniedStatusDialog,
} from "@duosoftbg/nacid-backoffice-components";
import FoAppAcceptFormInitializer from "../../common/accept/FoAppAcceptFormInitializer";
import styled from "styled-components";
import { useState } from "react";
import { useTranslation } from "react-i18next";
import SarAcceptForm from "../../sar/accept/SarAcceptForm";
import UdirecAcceptForm from "../../udirec/accept/UdirecAcceptForm";
import DocrecAcceptForm from "../../docrec/accept/DocrecAcceptForm";
import BaseUniversityDialogsProvider from "../sections/educationData/university/components/dialog/BaseUniversityDialogsProvider";
import FoAppAcceptControlPanel from "./FoAppAcceptControlPanel";
import FoAppSectionTitle from "./FoAppSectionTitle";

const TabDivider = styled(DividerSpg)`
  border-width: 1px;
  margin-top: -2px;
`;

function a11yProps(key) {
  return {
    id: `app-accept-${key}`,
    "aria-controls": `app-accept-tabpanel-${key}`,
  };
}

const tabs = (appType: AppType) => [
  {
    key: 0,
    title: "t.efilledData",
    component: <EFilledData appType={appType} useFoId />,
  },
  {
    key: 1,
    title: "t.acceptForm",
    component: <FormRenderer appType={appType} />,
  },
  {
    key: 2,
    title: "t.notes.data",
    component: <FoAcceptNotes />,
  },
];

type FoAppAcceptProps = {
  appType: AppType;
};

const FoAppAccept = ({ appType }: FoAppAcceptProps) => {
  const [activeTab, setActiveTab] = useState(0);
  const { t } = useTranslation();

  const handleChange = (event, newValue) => {
    setActiveTab(newValue);
  };

  return (
    <>
      <FoAppSectionTitle appType={appType} />
      <FoAppAcceptSubTitle appType={appType} />
      <FoAppAcceptControlPanel appType={appType} />
      <CardSpg my={4} style={{ overflow: "visible" }}>
        <CardContent style={{ position: "relative" }}>
          <BoxSpg>
            <FoAppAcceptFormInitializer activeTab={activeTab} appType={appType}>
              <Tabs style={{ marginTop: "-15px" }} variant="standard" value={activeTab} onChange={handleChange}>
                {tabs(appType).map((tab) => (
                  <Tab key={tab.key} label={t(tab.title)} {...a11yProps(tab.key)} />
                ))}
              </Tabs>
              <Box>
                <TabDivider />
                {tabs(appType).map((tab) => (
                  <div
                    key={tab.key}
                    role="tabpanel"
                    hidden={activeTab !== tab.key}
                    id={`app-accept-tabpanel-${tab.key}`}
                    aria-labelledby={`app-accept-tab-${tab.key}`}
                  >
                    {activeTab === tab.key && tab.component}
                  </div>
                ))}
              </Box>
            </FoAppAcceptFormInitializer>
            <DialogProviders appType={appType} />
          </BoxSpg>
        </CardContent>
      </CardSpg>
    </>
  );
};

const DialogProviders = ({ appType }) => {
  return (
    <>
      <PersonDialogsProvider />
      <AddressDialogsProvider />
      <BaseUniversityDialogsProvider />
      <FoAppRevertStatusDialog appType={appType} />
      <SaveFoAcceptNoteDialog />
      <DeleteFoAcceptNoteDialog />
      <FoAppDeniedStatusDialog />
    </>
  );
};

const FormRenderer = ({ appType }) => {
  switch (appType) {
    case AppType.UDIREC_APPLICATION: {
      return (
        <div>
          <ValidationErrors />
          <UdirecAcceptForm />
        </div>
      );
    }
    case AppType.SAR_APPLICATION: {
      return (
        <div>
          <ValidationErrors />
          <SarAcceptForm />
        </div>
      );
    }
    case AppType.DOCREC_APPLICATION: {
      return (
        <div>
          <ValidationErrors />
          <DocrecAcceptForm />
        </div>
      );
    }
  }

  return null;
};

export default FoAppAccept;
