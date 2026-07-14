import { WithChildren, AppPageWrapper } from "@duosoftbg/nacid-components";
import Breadcrumbs from "./Breadcrumbs";
import React from "react";

type PageWrapperProps = WithChildren<{
  title: string | React.ReactNode;
  centerTitle?: boolean;
  hidePageTitle?: boolean;
  helmetTitle?: string;
}>;

const PageWrapper = (props: PageWrapperProps) => {
  const { title, centerTitle, children, hidePageTitle = false, helmetTitle } = props;

  return (
    <AppPageWrapper
      children={children}
      helmetTitle={helmetTitle ? helmetTitle : (title as string)}
      title={hidePageTitle ? "" : title}
      centerTitle={centerTitle}
      breadcrumbs={<Breadcrumbs />}
    />
  );
};

export default PageWrapper;
