import i18n from "i18next";

export const initializeYup = (yup) => {
  yup.setLocale({
    string: {
      email: i18n.t("validation.field.email.bad.format"),
      matches: i18n.t("validation.field.invalid"),
    },
    mixed: {
      default: i18n.t("validation.field.invalid"),
      required: i18n.t("validation.field.required"),
    },
    array: {
      min: i18n.t("validation.field.required"),
    },
  });
};
