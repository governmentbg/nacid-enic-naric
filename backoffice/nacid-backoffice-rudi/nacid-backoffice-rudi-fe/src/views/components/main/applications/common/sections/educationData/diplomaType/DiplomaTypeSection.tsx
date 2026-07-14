import {
  DateFormField,
  FormSection,
  GridContainer,
  GridItem,
  GridSpg,
  InputFormField,
  useExternalFormField,
} from "@duosoftbg/nacid-components";
import React from "react";
import { useFormContext } from "react-hook-form";
import { AppType } from "@duosoftbg/nacid-backoffice-components";

type BaseDiplomaTypeSectionProps = {
  appType: AppType;
  baseUniversityIdPointer?: string;
  titleSection?: string;
};

const DiplomaTypeSection = ({
  appType,
  baseUniversityIdPointer = "baseUniversityId",
  titleSection = "t.base.diplomaType.details",
}: BaseDiplomaTypeSectionProps) => {
  const { getValues } = useFormContext();
  const tempDataKey = `${appType}-${getValues("applicationId")}`;
  const baseUniversityId = useExternalFormField({ key: tempDataKey, pointer: baseUniversityIdPointer });

  return (
    <>
      {baseUniversityId && (
        <FormSection label={titleSection}>
          <GridSpg container spacing={1}>
            <GridSpg item xs={12}>
              <GridContainer spacing={4} mt={0}>
                <GridItem sm={3} md={3}>
                  <InputFormField fieldName={"diplomaNumber"} labelCode={"l.diplomaNumber"} />
                </GridItem>
                <GridItem sm={3} md={3}>
                  <DateFormField fieldName={"diplomaDate"} labelCode={"l.diplomaDate"} maxDate={new Date()} />
                </GridItem>
                <GridItem sm={3} md={3}>
                  <InputFormField fieldName={"diplomaSeries"} labelCode={"l.diplomaSeries"} />
                </GridItem>
                <GridItem sm={3} md={3}>
                  <InputFormField fieldName={"diplomaRegistrationNumber"} labelCode={"l.diplomaRegistrationNumber"} />
                </GridItem>
              </GridContainer>
            </GridSpg>
          </GridSpg>
        </FormSection>
      )}
    </>
  );
};

export default DiplomaTypeSection;
