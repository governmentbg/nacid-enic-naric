import {
  DateFormField,
  GridContainer,
  GridItem,
  InputFormField,
  NomenclatureAutocompleteFormField,
  TextareaFormField,
} from "@duosoftbg/nacid-components";
import { languageThunk } from "../../../../../../../store/redux/slice/AppData/language";
import { useWatch } from "react-hook-form";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";

const DocDissertationFormFields = () => {
  const thunkStateLanguage = useAppSelector((state) => {
    return state.AppData.Language;
  });

  const dissertationLanguageId = useWatch({ name: "dissertationLanguage.id" });

  return (
    <>
      <GridContainer>
        <GridItem>
          <InputFormField required={true} fieldName={"dissertationTheme"} labelCode={"l.dissertationTheme"} />
        </GridItem>
        <GridItem>
          <InputFormField required={true} fieldName={"dissertationThemeEn"} labelCode={"l.dissertationThemeEn"} />
        </GridItem>
        <GridItem>
          <DateFormField required={true} fieldName={"dissertationDate"} labelCode={"l.dissertationDate"} />
        </GridItem>
      </GridContainer>
      <GridContainer>
        <GridItem>
          <NomenclatureAutocompleteFormField
            onlyActive
            required={true}
            fieldName={"dissertationLanguage.id"}
            labelCode={"l.dissertationLanguage"}
            thunkState={thunkStateLanguage}
            thunkFn={languageThunk}
            initialValue={dissertationLanguageId}
          />
        </GridItem>
        <GridItem>
          <InputFormField
            required={true}
            type={"number"}
            fieldName={"dissertationBiblioTitlesCount"}
            labelCode={"l.dissertationBiblioTitlesCount"}
          />
        </GridItem>
        <GridItem>
          <InputFormField
            required={true}
            type={"number"}
            fieldName={"dissertationPagesCount"}
            labelCode={"l.dissertationPagesCount"}
          />
        </GridItem>
      </GridContainer>
      <GridContainer>
        <GridItem sm={12} md={12}>
          <TextareaFormField
            required={true}
            fieldName={"dissertationAnnotation"}
            labelCode={"l.dissertationAnnotation"}
            rows={3}
          />
        </GridItem>
      </GridContainer>
      <GridContainer>
        <GridItem sm={12} md={12}>
          <TextareaFormField
            required={true}
            fieldName={"dissertationAnnotationEn"}
            labelCode={"l.dissertationAnnotationEn"}
            rows={3}
          />
        </GridItem>
      </GridContainer>
    </>
  );
};
export default DocDissertationFormFields;
