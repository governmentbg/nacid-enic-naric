import { WithChildren, AppPageWrapper } from "@duosoftbg/nacid-components";
import Breadcrumbs from "./Breadcrumbs";
import React from "react";

type PageWrapperProps = WithChildren<{
  title: string | React.ReactNode;
  centerTitle?: boolean;
}>;

const PageWrapper = (props: PageWrapperProps) => {
  const { title, centerTitle, children } = props;

  return <AppPageWrapper children={children} title={title} centerTitle={centerTitle} breadcrumbs={<Breadcrumbs />} />;
};

export default PageWrapper;
