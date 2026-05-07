import * as React from "react";
import PropTypes from "prop-types";
import Typography from "@mui/material/Typography";
import { BoxSpg } from "@duosoftbg/nacid-components";

ServiceTabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
};

function ServiceTabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`service-tabpanel-${index}`}
      aria-labelledby={`service-tab-${index}`}
      {...other}
    >
      {value === index && (
        <BoxSpg p={3}>
          <Typography variant="h6" component="div">
            {children}
          </Typography>
        </BoxSpg>
      )}
    </div>
  );
}

export default ServiceTabPanel;
