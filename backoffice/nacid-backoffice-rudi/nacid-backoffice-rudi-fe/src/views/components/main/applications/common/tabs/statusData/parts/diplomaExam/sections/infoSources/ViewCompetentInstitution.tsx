import { useWatch } from "react-hook-form";
import { isNotEmpty } from "@duosoftbg/nacid-components";
import { LibraryComponentsControlActions } from "@duosoftbg/nacid-backoffice-components";
import useAppDispatch from "../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import { IconButton, Tooltip } from "@mui/material";
import VisibilityIcon from "@mui/icons-material/Visibility";
import React from "react";
import { useTranslation } from "react-i18next";

const ViewCompetentInstitution = () => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const competentInstitutionId = useWatch({ name: "competentInstitutionId" });

  const handleOpenViewModal = () => {
    dispatch(
      LibraryComponentsControlActions.competentInstitutionControlActions.openModal({
        id: competentInstitutionId,
        modalType: "view",
      }),
    );
  };

  return (
    <>
      {isNotEmpty(competentInstitutionId) && (
        <Tooltip title={t("l.btn.view.v2")}>
          <IconButton color="primary" onClick={handleOpenViewModal}>
            <VisibilityIcon />
          </IconButton>
        </Tooltip>
      )}
    </>
  );
};
export default ViewCompetentInstitution;
