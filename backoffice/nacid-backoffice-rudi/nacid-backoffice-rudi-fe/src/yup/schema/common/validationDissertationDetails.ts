import { initializeYup } from "@duosoftbg/nacid-backoffice-components";
import * as yup from "yup";
import i18n from "i18next";

export const validationDissertationDetails = () => {
  initializeYup(yup);
  const schema = yup.object({
    scientificSupervisor: yup.string().optional().nullable().max(1500, i18n.t("m.validation.field.longer.than.1500")),
    thesisTopic: yup.string().optional().nullable().max(1500, i18n.t("m.validation.field.longer.than.1500")),
    thesisTopicEn: yup.string().optional().nullable().max(1500, i18n.t("m.validation.field.longer.than.1500")),
    scientificSupervisorEn: yup.string().optional().nullable().max(1500, i18n.t("m.validation.field.longer.than.1500")),
    reviewers: yup.string().optional().nullable().max(1500, i18n.t("m.validation.field.longer.than.1500")),
    reviewersEn: yup.string().optional().nullable().max(1500, i18n.t("m.validation.field.longer.than.1500")),
    juryChair: yup.string().optional().nullable().max(1500, i18n.t("m.validation.field.longer.than.1500")),
    juryChairEn: yup.string().optional().nullable().max(1500, i18n.t("m.validation.field.longer.than.1500")),
    juryMembers: yup.string().optional().nullable().max(1500, i18n.t("m.validation.field.longer.than.1500")),
    juryMembersEn: yup.string().optional().nullable().max(1500, i18n.t("m.validation.field.longer.than.1500")),
    thesisAnnotation: yup.string().optional().nullable().max(1500, i18n.t("m.validation.field.longer.than.1500")),
    thesisAnnotationEn: yup.string().optional().nullable().max(1500, i18n.t("m.validation.field.longer.than.1500")),
  });

  return schema;
};
