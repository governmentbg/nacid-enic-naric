import { GridContainer, GridItem, RadiosFormField } from "@duosoftbg/nacid-components";
import { ApplicantType } from "../../../../../../../types/common/personTypes";
import { useTranslation } from "react-i18next";

const ApplicantTypeFormFields = ({ types }: { types: ApplicantType[] }) => {
  const { t } = useTranslation();

  if (!types || types.length === 0) {
    return null;
  }
  return (
    <GridContainer>
      <GridItem sm={12} md={12}>
        <RadiosFormField
          required={true}
          isInline={true}
          fieldName={"applicant.applicantType"}
          labelCode={"l.applicant.type"}
          radioOptions={types.map((type) => {
            return { value: type, text: t("l.applicant.type." + type.valueOf()) };
          })}
        ></RadiosFormField>
      </GridItem>
    </GridContainer>
  );
};
export default ApplicantTypeFormFields;
