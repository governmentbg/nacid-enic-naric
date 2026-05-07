import { useTranslation } from "react-i18next";
import { TableButton, TempFormDataActions, useReloadWatcherWriter } from "@duosoftbg/nacid-components";
import { ReloadWatcherObject } from "@duosoftbg/nacid-backoffice-components";
import useAppDispatch from "../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import { UniversityControlActions } from "../../../../../../../../../../../store/redux/slice/ComponentsControl/baseUniversityControl";
import { store } from "../../../../../../../../../../../store/redux/store";

type ImportTempDataUniversityButtonProps = {
  label?: string;
  universityId: null;
};

const ImportTempDataUniversityButton = ({ label = "l.btn.add", universityId }: ImportTempDataUniversityButtonProps) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const { updateReloadWatcher } = useReloadWatcherWriter();

  const { universityIdPointer, tempDataKey: universityTDK } =
    store.getState().ComponentsControl.universityControl.modals.search;

  const handleClick = (id) => (e) => {
    dispatch(TempFormDataActions.setTempData({ key: universityTDK, pointer: universityIdPointer, data: id }));
    updateReloadWatcher(ReloadWatcherObject.build(universityTDK, universityIdPointer));
    dispatch(UniversityControlActions.closeSearchUniversityModal({}));
  };

  return <TableButton title={t(label)} type={"create"} onClick={handleClick(universityId)} />;
};

export default ImportTempDataUniversityButton;
