import {
  GridContainer,
  GridItem,
  TextareaFormField,
  YearFormField,
  CheckboxListFormField,
  ApplicationSubtype,
  ApplicationType,
} from "@duosoftbg/nacid-components";
import { useEffect } from "react";
import { languageThunk } from "../../../../../../../store/redux/slice/AppData/language";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";

const SearchDetailsFormFields = () => {
  const dispatch = useAppDispatch();

  const thunkStateLanguage = useAppSelector((state) => {
    return state.AppData.Language;
  });

  useEffect(() => {
    dispatch(languageThunk());
  }, [dispatch]);

  return (
    <>
      <GridContainer>
        <GridItem sm={12} md={12}>
          <TextareaFormField required={true} fieldName={"theme"} labelCode={"l.biblioReference.theme"} />
        </GridItem>
      </GridContainer>
      <GridContainer>
        <GridItem sm={12} md={12}>
          <TextareaFormField required={true} fieldName={"keywords"} labelCode={"l.biblioReference.keywords"} />
        </GridItem>
      </GridContainer>
      <GridContainer>
        <GridItem sm={6} md={6}>
          <YearFormField required={true} fieldName={"searchFrom"} labelCode={"l.biblioReference.searchFrom"} />
        </GridItem>
        <GridItem sm={6} md={6}>
          <YearFormField required={true} fieldName={"searchTo"} labelCode={"l.biblioReference.searchTo"} />
        </GridItem>
      </GridContainer>
      <GridContainer>
        <GridItem sm={12} md={12}>
          {thunkStateLanguage.data && thunkStateLanguage.data.length > 0 && (
            <CheckboxListFormField
              required={true}
              row={true}
              fieldName={"searchLanguages"}
              labelCode={"l.biblioReference.searchLanguages"}
              checkboxOptions={thunkStateLanguage.data
                .filter(
                  (lang) =>
                    lang.applicationConfigs &&
                    lang.applicationConfigs.length > 0 &&
                    lang.applicationConfigs.filter(
                      (cfg) =>
                        cfg.applicationType === ApplicationType.LIBRARY &&
                        cfg.applicationSubtype === ApplicationSubtype.BIBLIO_REFERENCE
                    )
                )
                .map((lang) => {
                  return { value: lang.id, text: lang.name, active: lang.isActive };
                })}
              valuesAreEqual={(checkboxVal, arrayVal) => checkboxVal === arrayVal.id}
              selectValueTransform={(selected) => {
                return {
                  id: selected,
                  name: "",
                };
              }}
            />
          )}
        </GridItem>
      </GridContainer>
    </>
  );
};
export default SearchDetailsFormFields;
