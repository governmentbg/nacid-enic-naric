import styled from "styled-components";
import { BoxSpg } from "@duosoftbg/nacid-components";
import { AccordionSummary } from "@mui/material";

export const AccordionItemBox = styled(BoxSpg)<{ height?: string }>`
  border: 1px solid #d8d8d84f;
  height: ${(props) => props.height && props.height};
  overflow: auto;
  padding: 1px;
  background-color: #d8d8d84f;
`;

export const AccordionSummaryStld = styled(AccordionSummary)`
  background-color: #d8d8d84f;
`;
