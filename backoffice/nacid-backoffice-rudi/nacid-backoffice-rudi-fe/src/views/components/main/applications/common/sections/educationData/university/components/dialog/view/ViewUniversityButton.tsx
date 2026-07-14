import { useTranslation } from "react-i18next";
import { TableButton } from "@duosoftbg/nacid-components";
import * as React from "react";
import useAppDispatch from "../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import { UniversityControlActions } from "../../../../../../../../../../../store/redux/slice/ComponentsControl/baseUniversityControl";

type ViewUniversityButtonProps = {
  label?: string;
  universityId: null;
};

const ViewUniversityButton = ({ label = "l.btn.view.v2", universityId }: ViewUniversityButtonProps) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  //TODO
  const handleClick = (universityId) => (e) => {
    dispatch(UniversityControlActions.openViewUniversityModal({ universityId }));
  };

  return <TableButton title={t(label)} type={"more"} onClick={handleClick(universityId)} />;
};

export default ViewUniversityButton;
