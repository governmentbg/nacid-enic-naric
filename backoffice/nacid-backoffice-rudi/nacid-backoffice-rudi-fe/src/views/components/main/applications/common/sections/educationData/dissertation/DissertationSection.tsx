import {
  DateFormField,
  FormSection,
  GridContainer,
  GridItem,
  GridSpg,
  InputFormField,
  TextareaFormField,
} from "@duosoftbg/nacid-components";
import React from "react";
import { AppType, LanguageSelectField } from "@duosoftbg/nacid-backoffice-components";
import GraduationDocTypeField from "../../../components/GraduationDocTypeFiled";
import { useFormContext } from "react-hook-form";

type BaseDissertationSectionProps = {
  appType: AppType;
  titleSection?: string;
  baseUniversityIdPointer?: string;
};

const DissertationSection = ({
  titleSection = "t.dissertation.details",
  baseUniversityIdPointer = "baseUniversityId",
  appType,
}: BaseDissertationSectionProps) => {
  const { getValues } = useFormContext();
  const tempDataKey = `${appType}-${getValues("applicationId")}`;
  return (
    <>
      <FormSection label={titleSection}>
        <GridSpg container spacing={1}>
          <GridSpg item xs={12}>
            <GridContainer spacing={4} mt={0}>
              <GridItem sm={6} md={6}>
                <InputFormField fieldName={"thesisTopic"} labelCode={"l.thesisTopic"} />
              </GridItem>
              <GridItem sm={6} md={6}>
                <InputFormField fieldName={"thesisTopicEn"} labelCode={"l.thesisTopicEn"} />
              </GridItem>
              <GridItem sm={6} md={6}>
                <DateFormField fieldName={"thesisDefenceDate"} labelCode={"l.thesisDefenceDate"} maxDate={new Date()} />
              </GridItem>
              <GridItem sm={6} md={6}>
                <LanguageSelectField field={"thesisLanguage"} label={"l.thesisLanguage"} />
              </GridItem>
              <GridItem sm={6} md={6}>
                <InputFormField fieldName={"thesisBibliography"} labelCode={"l.thesisBibliography"} />
              </GridItem>
              <GridItem sm={6} md={6}>
                <InputFormField fieldName={"thesisVolume"} labelCode={"l.thesisVolume"} />
              </GridItem>
              <GridItem sm={6} md={6}>
                <TextareaFormField fieldName={"thesisAnnotation"} labelCode={"l.thesisAnnotation"} />
              </GridItem>
              <GridItem sm={6} md={6}>
                <TextareaFormField fieldName={"thesisAnnotationEn"} labelCode={"l.thesisAnnotationEn"} />
              </GridItem>
              {/*<ProfGroupFields />*/}
              <GraduationDocTypeField tempDataKey={tempDataKey} baseUniversityIdPointer={baseUniversityIdPointer} />
            </GridContainer>
          </GridSpg>
        </GridSpg>
      </FormSection>
    </>
  );
};

export default DissertationSection;
