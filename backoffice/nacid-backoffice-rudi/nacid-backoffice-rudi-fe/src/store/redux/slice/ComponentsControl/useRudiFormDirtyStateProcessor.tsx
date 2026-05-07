import { useFormDirtyStateProcessor } from "@duosoftbg/nacid-components";
import { FormDirtyStateProcessorFn } from "../../../../config/functions/formDirtyStateProcessorFn";

const useRudiFormDirtyStateProcessor = () => {
  return useFormDirtyStateProcessor({ functions: FormDirtyStateProcessorFn });
};

export default useRudiFormDirtyStateProcessor;
