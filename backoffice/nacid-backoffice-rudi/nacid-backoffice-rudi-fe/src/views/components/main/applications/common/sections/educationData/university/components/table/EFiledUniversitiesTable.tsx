import { useTranslation } from "react-i18next";
import { TableBody, TableCell, TableRow } from "@mui/material";
import React from "react";
import { GridContainer, GridItem, NacidTableSimple } from "@duosoftbg/nacid-components";

const EFiledUniversitiesTable = ({ universitiesData }) => {
  const { t } = useTranslation();

  const headCells = [
    { id: "name", label: t("l.university.name"), sortable: false },
    { id: "faculty", label: t("l.university.faculty"), sortable: false },
    { id: "universityContact", label: t("l.university.universityContact"), sortable: false },
  ];

  if (!universitiesData) {
    return null;
  }
  return (
    <GridContainer>
      <GridItem sm={12} md={12}>
        <NacidTableSimple headCells={headCells}>
          <TableBody>
            {universitiesData.map((uni, index) => (
              <TableRow key={index}>
                <TableCell>{uni.name}</TableCell>
                <TableCell>{uni.faculty}</TableCell>
                <TableCell>{uni.universityContact ? uni.universityContact : ""}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </NacidTableSimple>
      </GridItem>
    </GridContainer>
  );
};
export default EFiledUniversitiesTable;
