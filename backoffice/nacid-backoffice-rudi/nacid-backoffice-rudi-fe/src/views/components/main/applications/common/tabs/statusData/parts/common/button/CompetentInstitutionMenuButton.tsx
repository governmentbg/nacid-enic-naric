import useAppDispatch from "../../../../../../../../../../hooks/redux/base/useAppDispatch";
import React, { useState } from "react";
import { SectionMenu, SectionMenuItem } from "@duosoftbg/nacid-components";
import { faEdit, faPlus } from "@fortawesome/free-solid-svg-icons";
import { useFormContext } from "react-hook-form";
import { LibraryComponentsControlActions } from "@duosoftbg/nacid-backoffice-components";

type CompetentInstitutionMenuButtonProps = {
  withEdit?: boolean;
  withCreate?: boolean;
  withCountry?: boolean;
  countryField?: string;
  competentInstitutionId: string;
};

const CompetentInstitutionMenuButton = ({
  withEdit = true,
  withCreate = true,
  withCountry = true,
  countryField = "university.country.id",
  competentInstitutionId,
}: CompetentInstitutionMenuButtonProps) => {
  const dispatch = useAppDispatch();
  const { getValues } = useFormContext();
  const [executeClose, setExecuteClose] = useState(false);

  const handleClickEdit = () => {
    dispatch(
      LibraryComponentsControlActions.competentInstitutionControlActions.openModal({
        id: competentInstitutionId,
        modalType: "edit",
      }),
    );
    setExecuteClose(true);
  };

  const handleClickCreate = () => {
    dispatch(
      LibraryComponentsControlActions.competentInstitutionControlActions.openModal({
        id: "",
        modalType: "edit",
        countryId: withCountry ? getValues(countryField) : null,
      }),
    );
    setExecuteClose(true);
  };

  return (
    <SectionMenu executeClose={executeClose} setExecuteClose={setExecuteClose}>
      {withEdit && competentInstitutionId && (
        <SectionMenuItem label={"l.btn.edit.v2"} faIcon={faEdit} onClick={handleClickEdit} />
      )}
      {withCreate && <SectionMenuItem label={"l.btn.create"} faIcon={faPlus} onClick={handleClickCreate} />}
    </SectionMenu>
  );
};
export default CompetentInstitutionMenuButton;
