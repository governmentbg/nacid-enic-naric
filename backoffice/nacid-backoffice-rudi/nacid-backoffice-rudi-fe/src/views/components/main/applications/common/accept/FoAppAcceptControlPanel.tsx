import * as React from "react";
import { BoxSpg, GridSpg, TextButton } from "@duosoftbg/nacid-components";
import { AcceptAppsViewDataActions } from "../../../../../../store/redux/slice/ComponentsControl/acceptAppsViewDataControl";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";
import { Visibility, VisibilityOff, Undo } from "@mui/icons-material";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { useTranslation } from "react-i18next";
import { useParams } from "react-router-dom";
import { AppType, FoAppDenyButton, LibraryComponentsControlActions } from "@duosoftbg/nacid-backoffice-components";

type FoAppAcceptControlPanelProps = {
  appType: AppType;
};

// eslint-disable-next-line no-empty-pattern
const FoAppAcceptControlPanel = ({ appType }: FoAppAcceptControlPanelProps) => {
  return (
    <BoxSpg mt={2} mb={-1}>
      <GridSpg
        style={{ background: "#008eff14", width: "100%", borderRadius: 5 }}
        p={4}
        pt={2}
        pb={5}
        ml={0}
        mr={0}
        container
        rowSpacing={3}
        columnSpacing={3}
      >
        <GridSpg style={{ padding: 0 }} item sm={4}>
          <RevertStatus />
        </GridSpg>
        <GridSpg style={{ padding: 0 }} item sm={4}>
          <ToggleRequestedData />
        </GridSpg>
        <FoAppDenyButton appType={appType} />
      </GridSpg>
    </BoxSpg>
  );
};

const ToggleRequestedData = () => {
  const dispatch = useAppDispatch();
  const { t } = useTranslation();

  const open = useAppSelector((state) => {
    return state.ComponentsControl.acceptAppsViewDataControl.open;
  });

  const handleClick = () => {
    dispatch(AcceptAppsViewDataActions.toggle());
  };
  return (
    <TextButton
      disableRipple
      startIcon={open ? <VisibilityOff /> : <Visibility />}
      color="primary"
      onClick={handleClick}
    >
      {open ? t("l.eFilled.data.hide") : t("l.eFilled.data.show")}
    </TextButton>
  );
};

type RevertStatusProps = {};

// eslint-disable-next-line no-empty-pattern
const RevertStatus = ({}: RevertStatusProps) => {
  const { t } = useTranslation();
  const { id } = useParams();
  const dispatch = useAppDispatch();

  const handleClick = () => {
    dispatch(LibraryComponentsControlActions.foRevertStatusControlActions.openModal({ id }));
  };

  return (
    <TextButton disableRipple startIcon={<Undo />} color="primary" onClick={handleClick}>
      {t("l.revert.status")}
    </TextButton>
  );
};

export default FoAppAcceptControlPanel;
