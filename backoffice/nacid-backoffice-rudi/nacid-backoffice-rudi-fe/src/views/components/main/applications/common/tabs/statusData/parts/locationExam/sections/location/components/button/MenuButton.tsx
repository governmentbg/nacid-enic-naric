import React, { useState } from "react";
import { SectionMenu, SectionMenuItem } from "@duosoftbg/nacid-components";
import { faEdit, faPlus } from "@fortawesome/free-solid-svg-icons";
import { useFormContext } from "react-hook-form";
import { LibraryComponentsControlActions } from "@duosoftbg/nacid-backoffice-components";
import useAppDispatch from "../../../../../../../../../../../../../hooks/redux/base/useAppDispatch";

type CompetentInstitutionMenuButtonProps = {
  withEdit?: boolean;
  withCreate?: boolean;
  tempDataKey: string;
  institutionField: string;
  institutionId: string;
};

const MenuButton = ({
  withEdit = true,
  withCreate = true,
  institutionField,
  institutionId,
}: CompetentInstitutionMenuButtonProps) => {
  const dispatch = useAppDispatch();
  const { getValues } = useFormContext();
  const [executeClose, setExecuteClose] = useState(false);

  const handleClickEdit = () => {
    dispatch(
      LibraryComponentsControlActions.trainingInstitutionControlActions.openModal({
        id: institutionId,
        fieldId: institutionField,
        modalType: "edit",
      }),
    );
    setExecuteClose(true);
  };

  const handleClickCreate = () => {
    dispatch(
      LibraryComponentsControlActions.trainingInstitutionControlActions.openModal({
        id: "",
        modalType: "edit",
        fieldId: institutionField,
        universities: getValues("universities"),
      }),
    );
    setExecuteClose(true);
  };

  return (
    <SectionMenu executeClose={executeClose} setExecuteClose={setExecuteClose}>
      {withEdit && institutionId && (
        <SectionMenuItem label={"l.btn.edit.v2"} faIcon={faEdit} onClick={handleClickEdit} />
      )}
      {withCreate && <SectionMenuItem label={"l.btn.create"} faIcon={faPlus} onClick={handleClickCreate} />}
    </SectionMenu>
  );
};
export default MenuButton;
