import React from "react";
import StatusDataInitializer from "./StatusDataInitializer";
import { Page403, SecurityGuard, SecurityRole } from "@duosoftbg/nacid-components";

const StatusData = ({ appType }) => {
  return (
    <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.StatusAccess]}>
      <StatusDataInitializer appType={appType} />
    </SecurityGuard>
  );
};

export default StatusData;
