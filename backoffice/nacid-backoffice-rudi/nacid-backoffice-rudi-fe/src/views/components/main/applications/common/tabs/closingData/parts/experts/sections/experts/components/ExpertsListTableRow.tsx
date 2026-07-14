import React, { Fragment, useState } from "react";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import { Box, Collapse, IconButton } from "@mui/material";
import { KeyboardArrowDown, KeyboardArrowUp } from "@mui/icons-material";
import { OptionTableCell, TableButton } from "@duosoftbg/nacid-components";
import useAppDispatch from "../../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import { openDeleteExpertModal } from "../../../../../../../../../../../../store/redux/slice/ComponentsControl/applicationsControl";
import ExpertSpecificDataViewBody from "./view/ExpertSpecificDataViewBody";
import ExpertPositionDataViewBody from "./view/ExpertPositionDataViewBody";

const ExpertsListTableRow = ({ expert, index, appType, applicationId, generateUrl }) => {
  const [openSection, setOpenSection] = useState(true);
  const dispatch = useAppDispatch();
  const handleOpenDeleteModal = (id) => {
    const payload = { id };
    dispatch(openDeleteExpertModal(payload));
  };

  return (
    <Fragment>
      <TableRow sx={{ "& > *": { borderBottom: "unset" } }}>
        <TableCell align="center">
          <IconButton aria-label="expand row" size="small" onClick={() => setOpenSection(!openSection)}>
            {openSection ? <KeyboardArrowUp /> : <KeyboardArrowDown />}
          </IconButton>
        </TableCell>
        <TableCell>{index + 1}</TableCell>
        <TableCell>
          {expert.commissionMember.firstName} {expert.commissionMember.middleName} {expert.commissionMember.lastName}
        </TableCell>
        <OptionTableCell>
          <TableButton type={"view"} to={generateUrl(appType, applicationId, expert.id, "view")} />
          <TableButton type={"edit"} to={generateUrl(appType, applicationId, expert.id, "edit")} />
          <TableButton type={"delete"} onClick={() => handleOpenDeleteModal(expert.id)} />
        </OptionTableCell>
      </TableRow>
      <TableRow>
        <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
          <Collapse in={openSection} timeout="auto" unmountOnExit>
            <Box style={{ marginLeft: "40px" }}>
              <ExpertSpecificDataViewBody expert={expert} />
              <ExpertPositionDataViewBody expert={expert} />
            </Box>
          </Collapse>
        </TableCell>
      </TableRow>
    </Fragment>
  );
};
export default ExpertsListTableRow;
