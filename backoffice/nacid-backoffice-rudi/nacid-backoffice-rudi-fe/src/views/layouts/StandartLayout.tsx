import { Box } from "@mui/material";
import React, { Fragment } from "react";
import { Outlet } from "react-router-dom";
import Header from "../components/common/layout/Header";
import Footer from "../components/common/layout/Footer";
import { BlockFormDialog, GlobalBackdrop, WithChildren } from "@duosoftbg/nacid-components";
import { AppModulesPageBlockerDialog, BackOfficeSidebar } from "@duosoftbg/nacid-backoffice-components";
import { sidebarData } from "../../config/sidebar/sidebarData";
import { FormDirtyStateProcessorFn } from "../../config/functions/formDirtyStateProcessorFn";

const StandartLayout = (props: WithChildren) => {
  const { children } = props;
  return (
    <Fragment>
      <Header />
      <BackOfficeSidebar sidebarData={sidebarData} headerTitle={"t.page.rudi"} />
      <Box>
        {children}
        <Outlet />
      </Box>
      <Footer />
      <GlobalBackdrop />
      <BlockFormDialog functions={FormDirtyStateProcessorFn} />
      <AppModulesPageBlockerDialog />
    </Fragment>
  );
};

export default StandartLayout;
