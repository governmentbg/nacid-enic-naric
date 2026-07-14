import useAppSelector from "./redux/base/useAppSelector";
import { useEffect } from "react";
import useAppDispatch from "./redux/base/useAppDispatch";
import { setFormResetValue } from "../store/redux/slice/FormReset/formReset";

const useFormReset = (applicationSubtype, resetFormData, resetFn) => {
  const dispatch = useAppDispatch();
  const formReset = useAppSelector((state) => {
    return state.FormReset[applicationSubtype];
  });

  useEffect(() => {
    if (formReset) {
      resetFn(resetFormData, { keepIsSubmitted: true, keepSubmitCount: true });
      dispatch(setFormResetValue({ applicationSubtype: applicationSubtype, resetValue: false }));
    }
  }, [formReset, resetFormData, resetFn, applicationSubtype, dispatch]);
};
export default useFormReset;
