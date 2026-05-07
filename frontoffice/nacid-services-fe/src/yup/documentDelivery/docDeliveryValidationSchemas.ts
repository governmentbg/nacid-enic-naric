import * as yup from "yup";
import { initializeYup } from "@duosoftbg/nacid-components";
import i18n from "i18next";
import { createFileValidationSchema } from "../common/document/documentValidationSchemas";

export const createBibliographicDetailsValidationSchema = () => {
  initializeYup(yup);

  const bibliographicDetailsValidationSchema = yup.object({
    entries: yup.array().min(1),
  });

  return bibliographicDetailsValidationSchema;
};

export const createDocBibliographicEntryDetailsValidationSchema = () => {
  initializeYup(yup);

  const schema = yup.object({
    electronicCatalogues: yup.boolean().test("required", i18n.t("validation.field.select"), function (value) {
      const { bgLibraries, foreignLibraries } = this.parent;
      if (!bgLibraries && !foreignLibraries) return value !== null && value;
      return true;
    }),
    bgLibraries: yup.boolean().test("required", i18n.t("validation.field.select"), function (value) {
      const { electronicCatalogues, foreignLibraries } = this.parent;
      if (!electronicCatalogues && !foreignLibraries) return value !== null && value;
      return true;
    }),
    foreignLibraries: yup.boolean().test("required", i18n.t("validation.field.select"), function (value) {
      const { electronicCatalogues, bgLibraries } = this.parent;
      if (!electronicCatalogues && !bgLibraries) return value !== null && value;
      return true;
    }),
    deliveryResultKind: yup.object({
      id: yup.string().required(),
    }),
    file: createFileValidationSchema(),
  });
  return schema;
};
