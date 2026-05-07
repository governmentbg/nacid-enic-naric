import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { Collapse } from "@mui/material";
import { BoxSpg } from "@duosoftbg/nacid-components";
import * as React from "react";

const ViewDataWrapper = ({ children }) => {
  const open = useAppSelector((state) => {
    return state.ComponentsControl.acceptAppsViewDataControl.open;
  });

  return (
    <Collapse in={open}>
      <BoxSpg p={3} mt={4} style={{ background: "rgb(248, 244, 255)", borderRadius: 5 }}>
        {children}
      </BoxSpg>
    </Collapse>
  );
};

export default ViewDataWrapper;
