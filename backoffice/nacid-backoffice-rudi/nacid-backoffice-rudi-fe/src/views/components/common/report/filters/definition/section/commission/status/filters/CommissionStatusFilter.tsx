import { GridItem, NomenclatureAutocompleteFormField } from "@duosoftbg/nacid-components";
import { commissionApplicationStatusesThunk } from "../../../../../../../../../../store/redux/slice/AppData/commissionApplicationStatuses";
import useAppSelector from "../../../../../../../../../../hooks/redux/base/useAppSelector";

const CommissionStatusFilter = ({ baseField }) => {
  const thunkStateCommissionApplicationStatuses = useAppSelector((state) => {
    return state.AppData.commissionApplicationStatuses;
  });

  return (
    <GridItem md={6} pt={0}>
      <NomenclatureAutocompleteFormField
        fieldName={`${baseField}.commissionStatus`}
        labelCode={"l.nomenclature.commissionStatus"}
        thunkFn={commissionApplicationStatusesThunk}
        thunkState={thunkStateCommissionApplicationStatuses}
      />
    </GridItem>
  );
};
export default CommissionStatusFilter;
