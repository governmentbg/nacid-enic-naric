import { useFormContext } from "react-hook-form";

const useFormViewData = () => {
  const { getValues } = useFormContext();
  const viewData = getValues("viewData");

  return { viewData };
};

export default useFormViewData;
