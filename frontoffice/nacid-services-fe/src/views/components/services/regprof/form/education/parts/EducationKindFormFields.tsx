import { GridContainer, SelectFormField, GridItem, EducationType } from "@duosoftbg/nacid-components";
import React from "react";
import { useFormContext, useWatch } from "react-hook-form";
import { useTranslation } from "react-i18next";

const EducationKindFormFields = () => {
  const { getValues } = useFormContext();
  const { t } = useTranslation();

  useWatch({ name: "educationSelected" });

  if (getValues().educationSelected) {
    return (
      <GridContainer>
        <GridItem>
          <SelectFormField
            required={true}
            fieldName={"education.kind"}
            labelCode={"l.regprof.education.kind"}
            selectOptions={Object.values(EducationType).map((val) => {
              return {
                value: val,
                text: t("l.education.kind." + val.valueOf()),
              };
            })}
            addEmptyOption={true}
          />
        </GridItem>
      </GridContainer>
    );
  } else {
    return null;
  }
};
export default EducationKindFormFields;
