import {
  CheckboxFormField,
  FormSection,
  GridContainer,
  GridItem,
  GridSpg,
  TextareaFormField,
} from "@duosoftbg/nacid-components";
import React, { useEffect } from "react";
import { useFormContext, useWatch } from "react-hook-form";
import { AppType } from "@duosoftbg/nacid-backoffice-components";

type RecognitionPurposeSectionProps = {
  appType: AppType;
  titleSection?: string;
};

const RecognitionPurposeSection = ({
  titleSection = "t.base.recognition.purpose.details",
}: RecognitionPurposeSectionProps) => {
  return (
    <>
      <FormSection label={titleSection}>
        <GridSpg container spacing={1}>
          <GridSpg item xs={12}>
            <GridContainer spacing={4} mt={0}>
              <GridItem sm={3} md={3}>
                <CheckboxFormField
                  fieldName={"recognitionPurposeContinueEducation"}
                  labelCode={"l.recognitionPurposeContinueEducation"}
                />
              </GridItem>
              <GridItem sm={3} md={3}>
                <CheckboxFormField fieldName={"recognitionPurposeWork"} labelCode={"l.recognitionPurposeWork"} />
              </GridItem>
              <GridItem sm={3} md={3}>
                <CheckboxFormField
                  fieldName={"recognitionPurposeProjectWork"}
                  labelCode={"l.recognitionPurposeProjectWork"}
                />
              </GridItem>
              <OtherRecognitionPurposeFields />
            </GridContainer>
          </GridSpg>
        </GridSpg>
      </FormSection>
    </>
  );
};

const OtherRecognitionPurposeFields = () => {
  const { setValue } = useFormContext();
  const recognitionPurposeOther = useWatch({ name: "recognitionPurposeOther" });

  useEffect(() => {
    if (!recognitionPurposeOther) {
      setValue("recognitionPurposeNotes", "");
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [recognitionPurposeOther]);

  return (
    <>
      <GridItem sm={3} md={3}>
        <CheckboxFormField fieldName={"recognitionPurposeOther"} labelCode={"l.recognitionPurposeOther"} />
      </GridItem>
      {recognitionPurposeOther && (
        <GridItem sm={12} md={12}>
          <TextareaFormField fieldName={"recognitionPurposeNotes"} labelCode={"l.recognitionPurposeNotes"} />
        </GridItem>
      )}
    </>
  );
};

export default RecognitionPurposeSection;
