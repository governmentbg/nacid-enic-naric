import {
  RadiosFormField,
  GridContainer,
  GridItem,
  BibliographicReferenceResultKind,
} from "@duosoftbg/nacid-components";
import { useFormContext, useWatch } from "react-hook-form";
import { useTranslation } from "react-i18next";

const SearchKindsFormFields = () => {
  const { t } = useTranslation();
  const { getValues } = useFormContext();

  useWatch({ name: "nacidSearch" });
  useWatch({ name: "foreignSearch" });

  return (
    <GridContainer>
      <GridItem sm={6} md={6}>
        {getValues().nacidSearch && (
          <RadiosFormField
            fieldName={"nacidSearchKind"}
            labelCode={"l.biblioReference.nacidSearchKind"}
            radioOptions={[
              {
                value: BibliographicReferenceResultKind.DESCRIPTIONS.valueOf(),
                text: t("l.biblioReference.resultKind.DESCRIPTIONS"),
              },
            ]}
          />
        )}
      </GridItem>
      <GridItem sm={6} md={6}>
        {getValues().foreignSearch && (
          <RadiosFormField
            fieldName={"foreignSearchKind"}
            labelCode={"l.biblioReference.foreignSearchKind"}
            radioOptions={[
              {
                value: BibliographicReferenceResultKind.DESCRIPTIONS.valueOf(),
                text: t("l.biblioReference.resultKind.DESCRIPTIONS"),
              },
              {
                value: BibliographicReferenceResultKind.DESCRIPTIONS_ABSTRACTS.valueOf(),
                text: t("l.biblioReference.resultKind.DESCRIPTIONS_ABSTRACTS"),
              },
            ]}
          />
        )}
      </GridItem>
    </GridContainer>
  );
};
export default SearchKindsFormFields;
