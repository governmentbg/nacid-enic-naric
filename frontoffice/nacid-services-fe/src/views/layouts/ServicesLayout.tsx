import { Box } from "@mui/material";
import { Fragment } from "react";
import { Outlet } from "react-router-dom";
import Header from "../components/common/layout/Header";
import Footer from "../components/common/layout/Footer";
import { GlobalBackdrop, WithChildren } from "@duosoftbg/nacid-components";
import { AppModulesPageBlockerDialog } from "@duosoftbg/nacid-frontoffice-components";

const ServicesLayout = (props: WithChildren) => {
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
      <AppModulesPageBlockerDialog />
    </Fragment>
  );
};

export default ServicesLayout;
