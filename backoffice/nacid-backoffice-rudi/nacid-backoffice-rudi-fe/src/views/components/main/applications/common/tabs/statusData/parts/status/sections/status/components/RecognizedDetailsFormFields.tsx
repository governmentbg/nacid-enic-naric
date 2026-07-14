import { useFormContext, useWatch } from "react-hook-form";
import { AppType, EducationLevelSelectField, Status } from "@duosoftbg/nacid-backoffice-components";
import {
  FilterFormStringArrayField,
  GridContainer,
  GridItem,
  ScrollableAsyncFormAutocomplete,
} from "@duosoftbg/nacid-components";
import {
  getQualificationsAutocomplete,
  getSpecialitiesAutocomplete,
} from "../../../../../../../../../../../../axios/api/services";
import * as React from "react";
import ProfGroupFields from "../../../../../../../components/ProfGroupFileds";

const RecognizedDetailsFormFields = ({ appType, reloadWatcher, applicationId }) => {
  const { getValues } = useFormContext();
  const statusId = useWatch({ name: "status.id" });

  if (appType === AppType.SAR_APPLICATION || statusId !== Status.ACKNOWLEDGED) {
    return null;
  }

  return (
    <>
      <GridContainer spacing={4} mt={0}>
        <GridItem sm={12} md={12}>
          <EducationLevelSelectField
            field={"recognizedEduLevel"}
            label={"l.recognized.educationLevel"}
            applicationId={applicationId}
          />
        </GridItem>
        <ProfGroupFields
          key={reloadWatcher}
          fieldName={"recognizedProfGroupId"}
          profGroupLabel={"l.recognized.profGroup"}
        />
      </GridContainer>

      {appType === AppType.UDIREC_APPLICATION && (
        <>
          <GridContainer spacing={4} mt={0}>
            <GridItem sm={12} md={12}>
              <ScrollableAsyncFormAutocomplete
                freeSolo={true}
                fieldName={`recognizedQualification`}
                selectedOption={
                  getValues("recognizedQualification")
                    ? {
                        id: getValues("recognizedQualification"),
                        name: getValues("recognizedQualification"),
                      }
                    : null
                }
                setOptionText={(option) => option.name}
                autocompleteFn={getQualificationsAutocomplete}
                label={"l.recognized.recognizedQualification"}
                reduceOptionObject={false}
                getOptionLabel={(option) => option.id + ""}
                setInputOnSelect={(option) => option.name}
              />
            </GridItem>
          </GridContainer>
          <FilterFormStringArrayField
            freeSolo={true}
            fieldName={`recognizedSpecialities`}
            listLabel={"l.selected.speciality"}
            autocompleteFn={getSpecialitiesAutocomplete}
            autocompleteLabel={"l.recognized.speciality"}
          />
        </>
      )}
    </>
  );
};
export default RecognizedDetailsFormFields;
