import useAppSelector from "../../../../../../../../../../../hooks/redux/base/useAppSelector";
import { AlertSpg, BoxSpg, DividerSpg, isEmpty, NacidTableSimple, OptionTableCell } from "@duosoftbg/nacid-components";
import TableBody from "@mui/material/TableBody";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import * as React from "react";
import { useTranslation } from "react-i18next";
import { Typography } from "@mui/material";
import ImportTempDataUniversityButton from "./ImportTempDataUniversityButton";
import CreateUniversityButton from "../create/CreateUniversityButton";
import ViewUniversityButton from "../view/ViewUniversityButton";

const headCells = [
  {
    id: "name",
    label: "l.name.v2",
  },
  {
    id: "orgName",
    label: "l.orgName",
  },
  {
    id: "country",
    label: "l.country",
  },
  {
    id: "options",
    label: "",
  },
];

const SearchUniversityDialogResult = ({ universityIdPointer, tempDataKey }) => {
  const { t } = useTranslation();

  const { records, status } = useAppSelector((state) => {
    return state.ComponentsControl.universityControl.searchTable;
  });

  if (status === "initial") {
    return null;
  }

  if (status === "error") {
    return <NoRecordsResult universityIdPointer={universityIdPointer} tempDataKey={tempDataKey} />;
  }

  if (isEmpty(records) || records.length < 1) {
    return <NoRecordsResult universityIdPointer={universityIdPointer} tempDataKey={tempDataKey} />;
  }

  return (
    <BoxSpg mt={3}>
      <DividerSpg />
      <Typography my={2}>{t("m.universitySearch.maxResults")}</Typography>
      <NacidTableSimple headCells={headCells}>
        <TableBody>
          {records.map((row) => (
            <TableRow hover key={row.id}>
              <TableCell>{row.bgName ?? ""}</TableCell>
              <TableCell>{row.orgName ?? ""}</TableCell>
              <TableCell>{row.country?.name ?? ""}</TableCell>
              <OptionTableCell>
                <ViewUniversityButton universityId={row.id} />
                <ImportTempDataUniversityButton universityId={row.id} />
              </OptionTableCell>
            </TableRow>
          ))}
        </TableBody>
      </NacidTableSimple>
      <CreateNewUniversityButton universityIdPointer={universityIdPointer} tempDataKey={tempDataKey} />
    </BoxSpg>
  );
};

const NoRecordsResult = ({ universityIdPointer, tempDataKey }) => {
  const { t } = useTranslation();
  return (
    <>
      <DividerSpg my={4} />
      <AlertSpg mt={3} severity="info">
        {t("m.noRecords")}
      </AlertSpg>
      <CreateNewUniversityButton universityIdPointer={universityIdPointer} tempDataKey={tempDataKey} />
    </>
  );
};

const CreateNewUniversityButton = ({ universityIdPointer, tempDataKey }) => {
  const { searchFormValues } = useAppSelector((state) => {
    return state.ComponentsControl.universityControl.modals.search;
  });

  return (
    <CreateUniversityButton
      universityIdPointer={universityIdPointer}
      tempDataKey={tempDataKey}
      defaultValues={searchFormValues}
    />
  );
};

export default SearchUniversityDialogResult;
