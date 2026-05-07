import { Download } from "@mui/icons-material";
import { AsyncCallArgs, TextButton, useAsyncCall } from "@duosoftbg/nacid-components";
import React from "react";
import { downloadDmsFileForDocAndId } from "../../../../../services/dmsFileCalls";
import { toast } from "react-toastify";
import { useTranslation } from "react-i18next";
import { useGoogleReCaptcha } from "react-google-recaptcha-v3";

const DmsFileDownload = ({ file }) => {
  const { asyncCall } = useAsyncCall();
  const { t } = useTranslation();
  const { executeRecaptcha } = useGoogleReCaptcha();

  const downloadDmsFile = async (file) => {
    const token = await executeRecaptcha("DMSFileDownload");
    const downloadAsyncArgs: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: downloadDmsFileForDocAndId(file.docId, file.id, token),
      processResponseErrors: false,
      onSuccess: (response) => {
        const downloadLink = window.document.createElement("a");
        let objectUrl = window.URL.createObjectURL(
          new Blob([response.data], { type: response.headers["content-type"] })
        );
        downloadLink.href = objectUrl;
        downloadLink.download = file.name;
        document.body.appendChild(downloadLink);
        downloadLink.click();
        document.body.removeChild(downloadLink);
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
      onClick={() => downloadDmsFile(file)}
      disableRipple
      startIcon={<Download />}
      color="primary"
    >
      {file.name}
    </TextButton>
  );
};
export default DmsFileDownload;
