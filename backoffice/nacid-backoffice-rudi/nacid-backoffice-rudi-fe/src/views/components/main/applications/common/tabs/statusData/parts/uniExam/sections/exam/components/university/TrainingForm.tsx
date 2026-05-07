import useAppDispatch from "../../../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../../../../../../../hooks/redux/base/useAppSelector";
import { useEffect } from "react";
import { CheckboxListFormField, GridItem } from "@duosoftbg/nacid-components";
import { trainingFormsThunk } from "@duosoftbg/nacid-backoffice-components";
import OtherTrainingFormNote from "./OtherTrainingFormNote";

const TrainingForm = () => {
  const dispatch = useAppDispatch();

  const thunkStateTrainingForms = useAppSelector((state) => {
    return state.ThunkData.trainingForms;
  });

  useEffect(() => {
    dispatch(trainingFormsThunk());
  }, [dispatch]);

  return (
    <>
      <GridItem sm={12} md={12}>
        <CheckboxListFormField
          labelCode={"l.uniExamination.trainingForm"}
          fieldName={`trainingForms`}
          row={true}
          checkboxOptions={thunkStateTrainingForms.data.map((option) => {
            return { value: option.id, text: option.name, active: option.isActive };
          })}
        />
      </GridItem>
      <OtherTrainingFormNote />
    </>
  );
};
export default TrainingForm;
