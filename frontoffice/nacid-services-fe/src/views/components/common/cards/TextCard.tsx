import * as React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import styled from "styled-components";
import { NavLink } from "react-router-dom";
import { useTranslation } from "react-i18next";
import Box from "@mui/material/Box";

const CardTitle = styled.span`
  position: relative;
`;

const RegistersCardWrapper = styled(Card)`
  height: 100%;

  &:hover {
    background: ${(props) => (props.theme.palette.mode === "dark" ? "rgba(6, 78, 148, 0.3)" : "#eee")};
  }
`;

const TextCard = ({ title, description, to = "/" }) => {
  const { t } = useTranslation();

  return (
    <NavLink to={to} style={{ textDecoration: "none" }}>
      <RegistersCardWrapper>
        <CardContent style={{ padding: "30px", paddingTop: "20px" }}>
          <Typography style={{ position: "relative" }} gutterBottom variant="h5" component="div">
            <CardTitle>{t(title)}</CardTitle>
          </Typography>
          <Box>
            <Typography variant="body2" color="text.secondary">
              {t(description)}
            </Typography>
          </Box>
        </CardContent>
      </RegistersCardWrapper>
    </NavLink>
  );
};

export default TextCard;
