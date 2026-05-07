import { DateFormField, GridContainer, GridItem, SelectFormField } from "@duosoftbg/nacid-components";
import React, { useEffect } from "react";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import { workdayDurationThunk } from "../../../../../../../store/redux/slice/AppData/workdayDuration";

const WorkingPeriodFormFields = ({ documentIndex, index }) => {
  const dispatch = useAppDispatch();

  const thunkStateWorkdayDuration = useAppSelector((state) => {
    return state.AppData.WorkdayDuration;
  });

  useEffect(() => {
    dispatch(workdayDurationThunk());
  }, [dispatch]);

  return (
    <>
      <GridContainer mt={0}>
        <GridItem>
          <DateFormField
            required={true}
            fieldName={`experience.experienceDocuments.${documentIndex}.workPeriods.${index}.fromDate`}
            labelCode={"l.regprof.experience.experienceDocument.workPeriods.fromDate"}
          />
        </GridItem>
        <GridItem>
          <DateFormField
            required={true}
            fieldName={`experience.experienceDocuments.${documentIndex}.workPeriods.${index}.toDate`}
            labelCode={"l.regprof.experience.experienceDocument.workPeriods.toDate"}
          />
        </GridItem>
        <GridItem>
          <SelectFormField
            required={true}
            fieldName={`experience.experienceDocuments.${documentIndex}.workPeriods.${index}.workDayHours.id`}
            labelCode={"l.regprof.experience.experienceDocument.workPeriods.workDayHours"}
            selectOptions={thunkStateWorkdayDuration.data.map((option) => {
              return { value: option.id, text: option.name, active: option.isActive };
            })}
            addEmptyOption={false}
          />
        </GridItem>
      </GridContainer>
    </>
  );
};
export default WorkingPeriodFormFields;
