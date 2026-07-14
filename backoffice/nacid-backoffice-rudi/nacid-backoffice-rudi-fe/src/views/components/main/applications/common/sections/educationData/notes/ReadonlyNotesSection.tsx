import {
  AlertSpg,
  DividerSpg,
  FormSection,
  GridContainer,
  GridItem,
  GridSkeleton,
  GridSpg,
  useAsyncCall,
} from "@duosoftbg/nacid-components";
import React, { useEffect, useState } from "react";
import { Typography } from "@mui/material";
import { useTranslation } from "react-i18next";
import { useParams } from "react-router-dom";

type NotesSectionProps = {
  titleSection?: string;
  selectNoteFn: (id: any) => any;
};

const ReadonlyNotesSection = ({ titleSection = "t.base.notes.details", selectNoteFn }: NotesSectionProps) => {
  const { asyncCall } = useAsyncCall();
  const { t } = useTranslation();
  const [notes, setNotes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  const { id } = useParams();

  useEffect(() => {
    asyncCall({
      promise: selectNoteFn(id),
      processResponseErrors: false,
      onSuccess: (response) => {
        setNotes(response);
        setLoading(false);
        setError(false);
      },
      onError: () => {
        setNotes([]);
        setError(true);
        setLoading(false);
      },
    });
    return () => {
      setNotes([]);
      setError(false);
      setLoading(true);
    };

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [id]);

  if (error) {
    return (
      <FormSection label={titleSection}>
        <AlertSpg mt={3} severity="error">
          {t("m.error.serverFetchingError")}
        </AlertSpg>
      </FormSection>
    );
  }

  if (loading) {
    return <GridSkeleton length={15} />;
  }

  return (
    <>
      {notes && notes.length > 0 && (
        <FormSection label={titleSection}>
          <GridSpg container spacing={1}>
            <GridSpg item xs={12}>
              <GridContainer spacing={4} mt={0}>
                {notes.map((note, index) => (
                  <Note key={index} note={note} index={index} />
                ))}
              </GridContainer>
            </GridSpg>
          </GridSpg>
        </FormSection>
      )}
    </>
  );
};

const Note = ({ index, note }) => {
  return (
    <>
      {index !== 0 && <DividerSpg my={4} />}
      <GridItem sm={12} md={12}>
        <Typography>{note}</Typography>
      </GridItem>
    </>
  );
};
export default ReadonlyNotesSection;
