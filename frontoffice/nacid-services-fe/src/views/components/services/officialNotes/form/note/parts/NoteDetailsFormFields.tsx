import {
  GridContainer,
  GridItem,
  TextareaFormField,
  CheckboxListFormField,
  OfficialNoteKind,
} from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";

const NoteDetailsFormFields = () => {
  const { t } = useTranslation();

  const officialNotesForm = useAppSelector((state) => {
    return state.Forms.OfficialNotesForm;
  });

  const createONList = () => {
    const list = Object.values(OfficialNoteKind)
      .filter((kind) => kind === OfficialNoteKind.DISSERTATION_NOTE || kind === OfficialNoteKind.POSITION_NOTE)
      .map((val) => {
        return {
          value: val.valueOf(),
          text: t("l.officialNote.kind." + val.valueOf()),
        };
      });
    if (
      officialNotesForm &&
      officialNotesForm.officialNotesDetails?.officialNotesKinds &&
      officialNotesForm.officialNotesDetails.officialNotesKinds.filter(
        (kind) => list.filter((internal) => internal.value === kind.valueOf()).length === 0
      ).length > 0
    ) {
      return [
        ...list,
        ...officialNotesForm.officialNotesDetails.officialNotesKinds
          .filter((kind) => list.filter((internal) => internal.value === kind.valueOf()).length === 0)
          .map((val) => {
            return {
              value: val.valueOf(),
              text: t("l.officialNote.kind." + val.valueOf()),
            };
          }),
      ];
    } else {
      return list;
    }
  };

  return (
    <>
      <GridContainer>
        <GridItem sm={12} md={12}>
          <CheckboxListFormField
            required={true}
            row={true}
            fieldName={"officialNotesKinds"}
            labelCode={"l.officialNotes.officialNotesKinds"}
            checkboxOptions={createONList()}
            disabled={officialNotesForm.submittedOrFinalized}
          />
        </GridItem>
      </GridContainer>
      <GridContainer>
        <GridItem sm={12} md={12}>
          <TextareaFormField fieldName={"additionalInformation"} labelCode={"l.officialNotes.additionalInformation"} />
        </GridItem>
      </GridContainer>
    </>
  );
};

export default NoteDetailsFormFields;
