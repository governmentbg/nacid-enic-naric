import { Box, Button, Typography, useMediaQuery } from "@mui/material";
import { useState } from "react";
import { useTranslation } from "react-i18next";
import { useTheme } from "@mui/material/styles";
import ShoppingCartCheckoutIcon from "@mui/icons-material/ShoppingCartCheckout";

const FeesSideButton = ({ fees, feesState }) => {
  const { t } = useTranslation();
  const theme = useTheme();
  const [open, setOpen] = useState(true);

  const isSmall = useMediaQuery(theme.breakpoints.down("sm"));

  const top = isSmall ? "400px" : "350px";

  const toggleVisibility = () => {
    setOpen(!open);
  };

  return (
    <Box
      sx={{
        position: "fixed",
        right: `${open ? "-2px" : "-100px"}`,
        top: top,
        zIndex: "1000",
      }}
      onClick={toggleVisibility}
    >
      <Box sx={{ width: "170px", height: "60px", float: "right" }}>
        <Button fullWidth variant="contained" startIcon={<ShoppingCartCheckoutIcon sx={{ width: "50px" }} />}>
          <Typography width={"120px"}>
            {feesState.loading && <>{t("m.loading")}</>}
            {feesState.error && <>{t("m.error")}</>}
            {fees.total && (
              <>{`${t("l.fee.totalFees")}: ${fees.total.toFixed(2)} ${t("l.fee." + fees.currencyCode)}`}</>
            )}
            {!fees.total && !feesState.loading && !feesState.error && <>{`${t("l.fee.totalFees")}: 0.00`}</>}
          </Typography>
        </Button>
      </Box>
    </Box>
  );
};
export default FeesSideButton;
