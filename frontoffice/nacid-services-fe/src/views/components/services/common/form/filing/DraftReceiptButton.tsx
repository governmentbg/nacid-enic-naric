import { Print } from "@mui/icons-material";
import { AsyncCallArgs, TextButton, useAsyncCall } from "@duosoftbg/nacid-components";
import React from "react";
import { useTranslation } from "react-i18next";
import { toast } from "react-toastify";
import { downloadDraftReceiptForId } from "../../../../../../services/serviceCalls";

const DraftReceiptButton = ({ basePath, appId }) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();

  const draftReceipt = () => {
    const downloadAsyncArgs: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: downloadDraftReceiptForId(basePath, appId),
      processResponseErrors: false,
      onSuccess: (response) => {
        let objectUrl = window.URL.createObjectURL(new Blob([response.data], { type: "application/pdf" }));
        window.open(objectUrl, "_blank");
        setTimeout(function () {
          window.URL.revokeObjectURL(objectUrl);
        }, 100);
      },
      onError: (errResponse) => {
        toast.error(t("m.generic.error.service.fail"));
      },
    };
    asyncCall(downloadAsyncArgs);
  };

  return (
    <TextButton
      size={"medium"}
      variant={"outlined"}
      disableRipple
      startIcon={<Print />}
      color="primary"
      ml={3}
      onClick={draftReceipt}
    >
      {t("l.btn.print")}
    </TextButton>
  );
};
export default DraftReceiptButton;
