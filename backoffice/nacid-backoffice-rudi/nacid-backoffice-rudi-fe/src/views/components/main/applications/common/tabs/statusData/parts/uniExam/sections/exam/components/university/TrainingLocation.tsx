import useAppDispatch from "../../../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../../../../../../../hooks/redux/base/useAppSelector";
import { useEffect } from "react";
import { uniExamTrainingLocationsThunk } from "../../../../../../../../../../../../../store/redux/slice/AppData/uniExamTrainingLocations";
import { GridItem, RadiosFormField } from "@duosoftbg/nacid-components";

const TrainingLocation = () => {
  const dispatch = useAppDispatch();

  const thunkStateUniExamTrainingLocations = useAppSelector((state) => {
    return state.AppData.uniExamTrainingLocations;
  });

  useEffect(() => {
    dispatch(uniExamTrainingLocationsThunk());
  }, [dispatch]);

  return (
    <GridItem sm={12} md={12}>
      <RadiosFormField
        fieldName={"trainingLocationId"}
        labelCode={"l.uniExamination.trainingLocation"}
        isInline={true}
        radioOptions={thunkStateUniExamTrainingLocations.data.map((option) => {
          return { value: option.id, text: option.name, active: option.isActive };
        })}
      />
    </GridItem>
  );
};
export default TrainingLocation;
