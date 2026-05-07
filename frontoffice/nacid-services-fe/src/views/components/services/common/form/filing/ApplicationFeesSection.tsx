import { useTranslation } from "react-i18next";
import {
  FormSection,
  GridItem,
  GridContainer,
  NacidTableSimple,
  AlertSpg,
  CircularTextLoader,
} from "@duosoftbg/nacid-components";
import { TableBody, TableCell, TableRow, Typography } from "@mui/material";
import React from "react";

const ApplicationFeesSection = ({ fees, feesState }) => {
  const { t } = useTranslation();

  const headCells = [
    { id: "feeName", label: t("h.fee.name"), sortable: false },
    { id: "feeAmount", label: t("h.fee.amount"), sortable: false },
  ];

  if (feesState.loading) {
    return <CircularTextLoader />;
  } else if (feesState.error) {
    return (
      <FormSection label={"t.fees.details"}>
        <GridContainer spacing={4} mt={0}>
          <GridItem sm={12} md={12}>
            <AlertSpg severity={"error"}>{t("m.fees.service.fail")}</AlertSpg>
          </GridItem>
        </GridContainer>
      </FormSection>
    );
  } else if (fees.forApproval) {
    return (
      <FormSection label={"t.fees.details"}>
        <GridContainer spacing={4} mt={0}>
          <GridItem sm={12} md={12}>
            <AlertSpg severity={"info"}>{t("m.fees.require.approval")}</AlertSpg>
          </GridItem>
        </GridContainer>
      </FormSection>
    );
  }
  return (
    <FormSection label={"t.fees.details"}>
      <GridContainer spacing={4} mt={0}>
        {fees.fees && fees.fees.length > 0 && (
          <>
            <GridItem sm={12} md={12}>
              <NacidTableSimple headCells={headCells}>
                <TableBody>
                  {fees.fees.map((fee) => (
                    <TableRow key={fee.feeKey}>
                      <TableCell>{fee.feeName}</TableCell>
                      <TableCell>{fee.feeAmount.toFixed(2)}</TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </NacidTableSimple>
            </GridItem>
            <GridItem sm={12} md={12}>
              <Typography variant={"h6"} align={"right"}>
                {`${t("l.fee.totalFees")}: ${fees.total.toFixed(2)} ${t("l.fee." + fees.currencyCode)}`}
              </Typography>
            </GridItem>
          </>
        )}
        {fees.fees && fees.fees.length === 0 && (
          <GridItem sm={12} md={12}>
            <AlertSpg severity={"success"}>{t("m.fees.none.apply")}</AlertSpg>
          </GridItem>
        )}
      </GridContainer>
    </FormSection>
  );
};
export default ApplicationFeesSection;
