import { CheckboxListFormField, GridContainer, GridItem } from "@duosoftbg/nacid-components";
import { useEffect } from "react";
import useAppDispatch from "../../../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../../../hooks/redux/base/useAppSelector";
import { legalNaturesThunk } from "../../../../../../../../../store/redux/slice/AppData/legalNatures";

const SarServicesFilters = ({ baseField }) => {
  const dispatch = useAppDispatch();

  const thunkStateLegalNature = useAppSelector((state) => {
    return state.AppData.legalNatures;
  });

  useEffect(() => {
    dispatch(legalNaturesThunk());
  }, [dispatch]);

  return (
    <GridContainer mt={0}>
      <GridItem sm={12} md={12} style={{ marginLeft: "5px" }}>
        <CheckboxListFormField
          fieldName={`${baseField}.legalNatureTypes`}
          row={true}
          size={"small"}
          checkboxOptions={thunkStateLegalNature.data.map((option) => {
            return { value: option.id, text: option.name };
          })}
        />
      </GridItem>
    </GridContainer>
  );
};
export default SarServicesFilters;
