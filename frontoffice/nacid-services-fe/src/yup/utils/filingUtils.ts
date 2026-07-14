import i18n from "i18next";
import { toast } from "react-toastify";

export const handleFilingErrors = (errResponse) => {
  if (errResponse && errResponse.errors && Array.isArray(errResponse.errors)) {
    let message = i18n.t("m.filing.application.fail.rules") + ": ";
    errResponse.errors.forEach((err) => {
      message = message + i18n.t(err.message) + "; ";
    });
    toast.error(message, { autoClose: 5000 });
  } else {
    toast.error(i18n.t("m.generic.error.service.fail"));
  }
};
