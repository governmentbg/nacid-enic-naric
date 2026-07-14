import { useFormContext, useWatch } from "react-hook-form";
import {
  InputFormField,
  CheckboxListFormField,
  GridItem,
  GridContainer,
  FormSection,
  ReferenceDataDomain,
  ReferenceDataCode,
} from "@duosoftbg/nacid-components";
import React, { useEffect } from "react";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import { recognitionAimThunk } from "../../../../../../../store/redux/slice/AppData/recognitionAim";

const RecognitionAimFormSection = () => {
  const { getValues } = useFormContext();
  const dispatch = useAppDispatch();

  const thunkStateRecognitionAim = useAppSelector((state) => {
    return state.AppData.RecognitionAim;
  });

  useWatch({ name: "recognitionAim" });

  useEffect(() => {
    dispatch(recognitionAimThunk());
  }, [dispatch]);

  return (
    <FormSection label={"t.recognitionAim"}>
      <GridContainer spacing={4} mt={0}>
        <GridItem sm={6} md={6}>
          <CheckboxListFormField
            required={true}
            fieldName={"recognitionAim"}
            labelCode={"t.recognitionAim"}
            checkboxOptions={thunkStateRecognitionAim.data.map((option) => {
              return { value: option.id, text: option.name, active: option.isActive };
            })}
            valuesAreEqual={(checkboxVal, arrayVal) => checkboxVal === arrayVal.id}
            selectValueTransform={(selected) => {
              return {
                id: selected,
                name: "",
                domain: ReferenceDataDomain.RECOGNITION_PURPOSE,
              };
            }}
          />
        </GridItem>
      </GridContainer>
      {getValues().recognitionAim.filter((val) => val.id === ReferenceDataCode.OTHER).length > 0 ? (
        <GridContainer>
          <GridItem sm={6} md={6}>
            <InputFormField fieldName={"recognitionAimOtherDetails"} labelCode={"l.recognitionAimOtherDetails"} />
          </GridItem>
        </GridContainer>
      ) : null}
    </FormSection>
  );
};
export default RecognitionAimFormSection;
