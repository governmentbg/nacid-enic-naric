import { Box } from "@mui/material";
import React, { Fragment } from "react";
import { Outlet } from "react-router-dom";
import Header from "../components/common/layout/Header";
import Footer from "../components/common/layout/Footer";
import { BlockFormDialog, GlobalBackdrop, WithChildren } from "@duosoftbg/nacid-components";
import { AppModulesPageBlockerDialog } from "@duosoftbg/nacid-backoffice-components";
import { FormDirtyStateProcessorFn } from "../../config/functions/formDirtyStateProcessorFn";

const NoSidebarLayout = (props: WithChildren) => {
  const { children } = props;

  return (
    <Fragment>
      <Header />
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

export default NoSidebarLayout;
