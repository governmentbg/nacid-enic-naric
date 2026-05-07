import { Chip, TableBody, TableCell, TableRow } from "@mui/material";
import { OptionTableCell, TableButton } from "@duosoftbg/nacid-components";
import React from "react";
import { useTranslation } from "react-i18next";

const CorrespondenceTableBody = ({ showTempNumber, correspondence, setCorrespondenceData }) => {
  const { t } = useTranslation();

  return (
    <TableBody>
      {correspondence.map((record) => (
        <TableRow key={record.id}>
          <TableCell>{record.dateCreated}</TableCell>
          {showTempNumber && <TableCell>{record.tempNumber}</TableCell>}
          <TableCell>
            <span>{record.about}</span>
            {record.dateRead ? (
              <Chip color={"default"} variant={"filled"} label={t("l.read")} sx={{ marginLeft: 4 }} />
            ) : (
              <Chip color={"info"} variant={"filled"} label={t("l.new")} sx={{ marginLeft: 4 }} />
            )}
          </TableCell>
          <TableCell>
            {record.registrationNumber}/{record.registrationDate}
          </TableCell>
          <TableCell>{record.dateRead}</TableCell>
          <OptionTableCell>
            <TableButton
              type={"view"}
              title={t("l.btn.view")}
              onClick={() => setCorrespondenceData({ correspondence: record, open: true })}
            />
          </OptionTableCell>
        </TableRow>
      ))}
    </TableBody>
  );
};
export default CorrespondenceTableBody;
