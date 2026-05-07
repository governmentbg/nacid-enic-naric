import useAppSelector from "./base/useAppSelector";
import { GroupName } from "../../types/filters";

const useSearchFiltersObject = (group: GroupName) => {
  return useAppSelector((state) => {
    return state.SearchData.backofficeSearchTable[group].filtersData;
  });
};

export default useSearchFiltersObject;
