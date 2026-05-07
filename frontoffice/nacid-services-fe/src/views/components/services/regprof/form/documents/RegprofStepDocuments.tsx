import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import DocumentsForm from "../../../common/form/document/DocumentsForm";
import { completeDocumentStep, setStepDocumentsEdited } from "../../../../../../store/redux/slice/Forms/regprofForm";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import useFormReset from "../../../../../../hooks/useFormReset";
import useStepDocuments from "../../../../../../hooks/useStepDocuments";
import { fileGroups } from "../../../../../../config/fileGroupConfig";

const RegprofStepDocuments = () => {
  const regprofForm = useAppSelector((state) => {
    return state.Forms.RegprofForm;
  });

  const { methods, onSubmit } = useStepDocuments(
    regprofForm,
    baseEndpointPaths.regprof,
    completeDocumentStep,
    setStepDocumentsEdited
  );

  useFormReset(ApplicationSubtype.REGULATED_PROFESSIONS, regprofForm.documentDetails, methods.reset);

  return (
    <DocumentsForm
      methods={methods}
      onSubmit={onSubmit}
      setDocumentsFormEdited={setStepDocumentsEdited}
      hasAttachmentForm={false}
      hasAttachmentType={true}
      applicationId={regprofForm.id}
      fileGroup={fileGroups.pdf}
      informingMessageCode={"m.regprof.files.info"}
    />
  );
};

export default RegprofStepDocuments;
