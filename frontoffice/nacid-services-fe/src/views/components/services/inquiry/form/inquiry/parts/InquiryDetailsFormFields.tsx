import {
  CheckboxListFormField,
  GridContainer,
  GridItem,
  InquiryKind,
  TextareaFormField,
  YearFormField,
  InputFormField,
  AlertSpg,
} from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";

const InquiryDetailsFormFields = () => {
  const { t } = useTranslation();

  const inquiryForm = useAppSelector((state) => {
    return state.Forms.InquiryForm;
  });

  return (
    <GridContainer spacing={4} mt={0}>
      <GridItem sm={12} md={12}>
        <CheckboxListFormField
          required={true}
          row={true}
          fieldName={"inquiryKinds"}
          labelCode={"l.inquiry.inquiryKinds"}
          checkboxOptions={Object.values(InquiryKind).map((val) => {
            return {
              value: val,
              text: t("l.inquiry.kind." + val.valueOf()),
            };
          })}
          disabled={inquiryForm.submittedOrFinalized}
        />
      </GridItem>
      <GridItem sm={12} md={12}>
        <InputFormField fieldName={"previousInquiryNum"} labelCode={"l.inquiry.previousInquiryNum"} />
      </GridItem>
      <GridItem sm={12} md={12}>
        <TextareaFormField fieldName={"inquiryAim"} labelCode={"l.inquiry.inquiryAim"} />
      </GridItem>
      <GridItem sm={6} md={6}>
        <YearFormField required={true} fieldName={"periodFrom"} labelCode={"l.inquiry.periodFrom"} />
      </GridItem>
      <GridItem sm={6} md={6}>
        <YearFormField required={true} fieldName={"periodTo"} labelCode={"l.inquiry.periodTo"} />
      </GridItem>
      <GridItem sm={12} md={12}>
        <AlertSpg severity={"info"}>{t("m.inquiry.period.info")}</AlertSpg>
      </GridItem>
    </GridContainer>
  );
};
export default InquiryDetailsFormFields;
