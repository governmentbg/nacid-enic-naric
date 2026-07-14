import { CheckboxFormField, GridContainer, GridItem } from "@duosoftbg/nacid-components";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";

const SearchChoiceFormFields = () => {
  const biblioReferenceForm = useAppSelector((state) => {
    return state.Forms.BiblioReferenceForm;
  });

  return (
    <GridContainer>
      <GridItem sm={6} md={6}>
        <CheckboxFormField
          fieldName={"nacidSearch"}
          labelCode={"l.biblioReference.nacidSearch"}
          isDisabled={biblioReferenceForm.submittedOrFinalized}
        />
      </GridItem>
      <GridItem sm={6} md={6}>
        <CheckboxFormField
          fieldName={"foreignSearch"}
          labelCode={"l.biblioReference.foreignSearch"}
          isDisabled={biblioReferenceForm.submittedOrFinalized}
        />
      </GridItem>
    </GridContainer>
  );
};
export default SearchChoiceFormFields;
