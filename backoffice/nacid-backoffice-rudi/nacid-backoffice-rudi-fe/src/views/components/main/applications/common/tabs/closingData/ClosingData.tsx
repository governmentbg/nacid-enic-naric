import * as React from "react";
import ExpertsPart from "./parts/experts/ExpertsPart";
import StatementsPart from "./parts/statements/StatementsPart";

const ClosingData = ({ appType }) => {
  return (
    <>
      <ExpertsPart appType={appType} />
      <StatementsPart appType={appType} />
    </>
  );
};

export default ClosingData;
