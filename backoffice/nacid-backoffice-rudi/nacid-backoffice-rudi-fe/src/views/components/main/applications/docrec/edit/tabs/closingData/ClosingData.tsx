import * as React from "react";
import RasPart from "./parts/ras/RasPart";
import ExpertsPart from "../../../../common/tabs/closingData/parts/experts/ExpertsPart";
import StatementsPart from "../../../../common/tabs/closingData/parts/statements/StatementsPart";

const ClosingData = ({ appType }) => {
  return (
    <>
      <ExpertsPart appType={appType} />
      <StatementsPart appType={appType} />
      <RasPart />
    </>
  );
};

export default ClosingData;
