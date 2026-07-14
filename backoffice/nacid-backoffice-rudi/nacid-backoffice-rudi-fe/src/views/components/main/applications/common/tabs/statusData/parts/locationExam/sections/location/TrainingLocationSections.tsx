import { FormSection, GridContainer, isArrayNotEmpty } from "@duosoftbg/nacid-components";
import React from "react";
import LocationPlaceInfo from "./components/LocationPlaceInfo";
import DiplomaUniversityFlagField from "./components/DiplomaUniversityFlagField";
import TrainingInstitutionField from "./components/TrainingInstitutionField";
import { useFormContext } from "react-hook-form";

const TrainingLocationSection = ({ baseField, location, tempDataKey, appType }) => {
  return (
    <FormSection label={"l.trainingLocationExam.location"}>
      <GridContainer spacing={4} mt={0}>
        <LocationPlaceInfo trainingLocation={location} />
        <DiplomaUniversityFlagField baseField={baseField} appType={appType} />
        <TrainingInstitutionField baseField={baseField} tempDataKey={tempDataKey} />
      </GridContainer>
    </FormSection>
  );
};

const TrainingLocationSections = ({ tempDataKey, appType }) => {
  const { getValues } = useFormContext();
  const trainingLocations = getValues("trainingLocations");

  return (
    <>
      {isArrayNotEmpty(trainingLocations) &&
        trainingLocations.map((location, index) => (
          <TrainingLocationSection
            key={location.id}
            baseField={`trainingLocations.${index}`}
            location={location}
            tempDataKey={tempDataKey}
            appType={appType}
          />
        ))}
    </>
  );
};
export default TrainingLocationSections;
