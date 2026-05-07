import React, { useEffect, useState } from "react";
import {
  BlockText,
  concatNotEmptyBy,
  FormSection,
  FormSectionAlert,
  FormSectionSkeleton,
  GridContainer,
  GridItem,
  isArrayNotEmpty,
  useAsyncCall,
} from "@duosoftbg/nacid-components";
import { getTrainingLocationExamUniversitiesData } from "../../../../../../../../../../../axios/api/services";
import { AppType } from "@duosoftbg/nacid-backoffice-components";

const UniversityInfo = ({ trainingLocationUniversity, sectionTitle }) => {
  return (
    <FormSection label={sectionTitle}>
      <GridContainer spacing={4} mt={0}>
        {trainingLocationUniversity?.university?.bgName && (
          <GridItem sm={12} md={6}>
            <BlockText label={"l.name"} text={trainingLocationUniversity?.university.bgName} />
          </GridItem>
        )}
        {trainingLocationUniversity?.university?.country && (
          <GridItem sm={12} md={3}>
            <BlockText
              label={"l.university.headquarters"}
              text={concatNotEmptyBy(", ")(
                trainingLocationUniversity?.university?.country.name,
                trainingLocationUniversity?.university?.address.city,
              )}
            />
          </GridItem>
        )}
        {trainingLocationUniversity?.uniExamTrainingLocation && (
          <GridItem sm={12} md={3}>
            <BlockText
              label={"l.uniExamination.trainingLocation"}
              text={trainingLocationUniversity?.uniExamTrainingLocation.name}
            />
          </GridItem>
        )}
      </GridContainer>
    </FormSection>
  );
};

const UniversitySections = ({ applicationId, appType }) => {
  const { asyncCall } = useAsyncCall();
  const [universitiesData, setUniversitiesData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  const sectionTitle = appType === AppType.DOCREC_APPLICATION ? "t.university.organization.data" : "t.university.data";

  useEffect(() => {
    if (applicationId) {
      asyncCall({
        promise: getTrainingLocationExamUniversitiesData(applicationId),
        processResponseErrors: false,
        onSuccess: (response) => {
          setUniversitiesData(response);
          setLoading(false);
          setError(false);
        },
        onError: () => {
          setError(true);
          setLoading(false);
        },
      });
    } else {
      setLoading(false);
      setUniversitiesData(null);
    }
  }, [asyncCall, applicationId]);

  if (loading) {
    return <FormSectionSkeleton sectionTitle={sectionTitle} />;
  }

  if (error) {
    return <FormSectionAlert sectionTitle={sectionTitle} />;
  }

  if (isArrayNotEmpty(universitiesData)) {
    return (
      <>
        {universitiesData.map((trainingLocationUniversity) => (
          <UniversityInfo
            key={trainingLocationUniversity.university.id}
            trainingLocationUniversity={trainingLocationUniversity}
            sectionTitle={sectionTitle}
          />
        ))}
      </>
    );
  }
  return null;
};

export default UniversitySections;
