import { SectionMenu, SectionMenuItem, TempFormDataActions } from "@duosoftbg/nacid-components";
import { faEdit, faPlus, faRemove } from "@fortawesome/free-solid-svg-icons";
import React, { useState } from "react";
import useAppDispatch from "../../../../../../../../../../hooks/redux/base/useAppDispatch";
import { UniversityControlActions } from "../../../../../../../../../../store/redux/slice/ComponentsControl/baseUniversityControl";

type BaseUniversityMenuProps = {
  tempDataKey: string;
  universityId: number;
  universityIdPointer: string;
  withEdit?: boolean;
  withSearch?: boolean;
  withRemove?: boolean;
};

const UniversitySectionMenuButton = ({
  tempDataKey,
  universityIdPointer,
  universityId,
  withEdit = true,
  withSearch = true,
  withRemove = false,
}: BaseUniversityMenuProps) => {
  const dispatch = useAppDispatch();
  const [executeClose, setExecuteClose] = useState(false);

  const handleClickSearch = () => {
    dispatch(
      UniversityControlActions.openSearchUniversityModal({ universityIdPointer: universityIdPointer, tempDataKey }),
    );
    setExecuteClose(true);
  };

  const handleClickEdit = () => {
    dispatch(
      UniversityControlActions.openEditUniversityModal({
        universityId,
        universityIdPointer: universityIdPointer,
        tempDataKey,
      }),
    );
    setExecuteClose(true);
  };

  const handleClickRemove = () => {
    dispatch(TempFormDataActions.setTempData({ key: tempDataKey, pointer: universityIdPointer, data: null }));
    setExecuteClose(true);
  };

  return (
    <SectionMenu executeClose={executeClose} setExecuteClose={setExecuteClose}>
      {withSearch && <SectionMenuItem label={"l.btn.choose.another"} faIcon={faPlus} onClick={handleClickSearch} />}
      {withEdit && <SectionMenuItem label={"l.btn.technicalEditing"} faIcon={faEdit} onClick={handleClickEdit} />}
      {withRemove && <SectionMenuItem label={"l.btn.remove"} faIcon={faRemove} onClick={handleClickRemove} />}
    </SectionMenu>
  );
};

export default UniversitySectionMenuButton;
