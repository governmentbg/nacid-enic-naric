import { DialogTitle, Dialog, DialogContent, DialogActions, Button } from "@mui/material";
import { useTranslation } from "react-i18next";
import { useEffect, useState } from "react";
import CorrespondenceUnreadView from "./CorrespondenceUnreadView";
import CorrespondenceReadView from "./CorrespondenceReadView";

const CorrespondenceReadDialog = ({ open, onCloseDialog, correspondence }) => {
  const { t } = useTranslation();
  const [localCorrespondence, setLocalCorrespondence] = useState(null);
  const [hasReadChanged, setHasReadChanged] = useState(false);

  useEffect(() => {
    setLocalCorrespondence(correspondence);
    setHasReadChanged(false);
  }, [correspondence]);

  const closeDialog = () => {
    onCloseDialog(hasReadChanged);
  };

  if (!localCorrespondence) {
    return null;
  }
  return (
    <Dialog
      open={open}
      onClose={closeDialog}
      fullWidth={true}
      maxWidth={"md"}
      aria-labelledby="correspondence-view-dialog-title"
    >
      <DialogTitle id="correspondence-view-dialog-title">{t("t.correspondence.read.dialog.title")}</DialogTitle>
      <DialogContent dividers>
        {localCorrespondence.dateRead && <CorrespondenceReadView correspondence={localCorrespondence} />}
        {!localCorrespondence.dateRead && (
          <CorrespondenceUnreadView
            onRead={(readCorrespondence) => {
              setHasReadChanged(true);
              setLocalCorrespondence(readCorrespondence);
            }}
            correspondence={localCorrespondence}
          />
        )}
      </DialogContent>
      <DialogActions>
        <Button autoFocus onClick={closeDialog}>
          {t("l.btn.close")}
        </Button>
      </DialogActions>
    </Dialog>
  );
};
export default CorrespondenceReadDialog;
