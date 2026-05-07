import { useTranslation } from "react-i18next";
import { Alert, Button, Dialog, DialogActions, DialogContent, DialogTitle } from "@mui/material";
import useAppDispatch from "../../../../../hooks/redux/base/useAppDispatch";
import { setFormResetValue } from "../../../../../store/redux/slice/FormReset/formReset";

const StepEditedDialog = ({ applicationSubtype, open, onCloseDialog }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  return (
    <Dialog open={open} onClose={onCloseDialog}>
      <DialogTitle>{t("t.warn.dialog.title")}</DialogTitle>
      <DialogContent>
        <Alert severity={"warning"}>{t("m.form.edited.save.first")}</Alert>
      </DialogContent>
      <DialogActions>
        <Button
          onClick={() => {
            dispatch(setFormResetValue({ applicationSubtype: applicationSubtype, resetValue: true }));
            onCloseDialog();
          }}
          variant="contained"
          color="error"
        >
          {t("l.btn.reject.changes")}
        </Button>
        <Button variant="outlined" onClick={onCloseDialog}>
          {t("l.btn.close")}
        </Button>
      </DialogActions>
    </Dialog>
  );
};
export default StepEditedDialog;
