import {
  FormSection,
  GridContainer,
  GridItem,
  GridSpg,
  InputFormField,
  TextareaFormField,
  YearFormField,
} from "@duosoftbg/nacid-components";
import React from "react";
import { AppType, CountrySelectField } from "@duosoftbg/nacid-backoffice-components";
import { useWatch } from "react-hook-form";

type BaseSchoolSectionProps = {
  appType: AppType;
  titleSection?: string;
};

const SchoolSection = ({ titleSection = "t.base.school.details" }: BaseSchoolSectionProps) => {
  return (
    <>
      <FormSection label={titleSection}>
        <GridSpg container spacing={1}>
          <GridSpg item xs={12}>
            <GridContainer spacing={4} mt={0}>
              <GridItem sm={3} md={3}>
                <CountrySelectField field={"schoolCountry"} />
              </GridItem>
              <GridItem sm={3} md={3}>
                <InputFormField fieldName={"schoolCity"} labelCode={"l.schoolCity"} />
              </GridItem>
              <GridItem sm={3} md={3}>
                <InputFormField fieldName={"schoolName"} labelCode={"l.schoolName"} />
              </GridItem>
              <SchoolGraduationDateFields />
              <GridItem sm={12} md={12}>
                <TextareaFormField fieldName={"schoolNotes"} labelCode={"l.schoolNotes"} />
              </GridItem>
            </GridContainer>
          </GridSpg>
        </GridSpg>
      </FormSection>
    </>
  );
};

const SchoolGraduationDateFields = () => {
  const diplomaDate = useWatch({ name: "diplomaDate" });

  return (
    <GridItem sm={3} md={3}>
      <YearFormField
        fieldName={"schoolGraduationDate"}
        labelCode={"l.schoolGraduationDate"}
        maxDate={diplomaDate ? new Date(diplomaDate.substr(diplomaDate.length - 4) - 1, 0, 1) : new Date()}
      />
    </GridItem>
  );
};

export default SchoolSection;
