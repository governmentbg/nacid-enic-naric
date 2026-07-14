import {
  GridItem,
  SimpleFetchAutocompleteFormField,
  useExternalFormField,
  useReloadWatcherReader,
} from "@duosoftbg/nacid-components";
import React from "react";
import { useFormContext, useWatch } from "react-hook-form";
import { getTrainingInstitutionsByIds } from "../../../../../../../../../../../../axios/api/services";
import MenuButton from "./button/MenuButton";
import { ReloadWatcherObject } from "@duosoftbg/nacid-backoffice-components";

const TrainingInstitutionField = ({ baseField, tempDataKey }) => {
  const { getValues } = useFormContext();

  const institutionField = `${baseField}.examinationTrainingInstitutionId`;
  const isNotUniInstitution = useWatch({ name: `${baseField}.isNotUniInstitution` });
  const trainingInstitutionId = useExternalFormField({ key: tempDataKey, pointer: institutionField });
  const { reloadWatcher } = useReloadWatcherReader(ReloadWatcherObject.TrainingInstitution.change());

  return (
    <>
      {isNotUniInstitution && (
        <GridItem sm={12} md={12}>
          <div>
            <div style={{ width: "97%", display: "inline-block" }}>
              <SimpleFetchAutocompleteFormField
                key={reloadWatcher}
                initialValue={trainingInstitutionId}
                fieldName={institutionField}
                labelCode={"l.trainingLocationExam.trainingInstitution"}
                autocompleteFn={() => getTrainingInstitutionsByIds(getValues("universityIds"))}
              />
            </div>
            <div style={{ width: "3%", display: "inline-block" }}>
              <MenuButton
                tempDataKey={tempDataKey}
                institutionField={institutionField}
                institutionId={trainingInstitutionId}
              />
            </div>
          </div>
        </GridItem>
      )}
    </>
  );
};
export default TrainingInstitutionField;
