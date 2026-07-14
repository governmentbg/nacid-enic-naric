import React, { Fragment } from "react";
import { DividerSpg, GridContainer, LabeledDataItem, TextSection } from "@duosoftbg/nacid-components";
import { AccordionDetails } from "@mui/material";
import useAppSelector from "../../../../../../../../hooks/redux/base/useAppSelector";
import { AppType } from "@duosoftbg/nacid-backoffice-components";

const getUniversityNameTranslated = (trainingCourseUniversities) => {
  if (trainingCourseUniversities) {
    let filtered = trainingCourseUniversities.filter((x) => x.ordNum === 1);
    if (filtered && filtered.length > 0) {
      let universityNameTranslated = filtered[0]?.universityNameTranslated;
      if (universityNameTranslated) {
        return universityNameTranslated;
      }
    }
  }

  return "";
};

type ViewBaseUniversitySectionProps = {
  appType: AppType;
};

const ViewBaseUniversitySection = ({ appType }: ViewBaseUniversitySectionProps) => {
  const viewData = useAppSelector((state) => {
    return state["ViewData"];
  });

  const trainingCourse = viewData.data.trainingCourse;
  const baseUniversity = trainingCourse?.baseUniversity;
  const address = baseUniversity?.address;
  const trainingCourseUniversities = trainingCourse?.trainingCourseUniversities;
  const baseUniNameTranslated = getUniversityNameTranslated(trainingCourseUniversities);

  if (!baseUniversity) {
    return null;
  }

  return (
    <AccordionDetails>
      <TextSection label={"t.base.university.details." + appType} withDivider>
        <GridContainer>
          <LabeledDataItem labelCode={"l.name"} data={baseUniversity?.bgName} />
          <LabeledDataItem labelCode={"l.originalName"} data={baseUniversity?.orgName} />
          <LabeledDataItem labelCode={"l.address.country"} data={baseUniversity?.country?.name} />
          <LabeledDataItem labelCode={"l.address.city"} data={address?.city} />
          <LabeledDataItem labelCode={"l.address.phone"} data={address?.phone} />
          <LabeledDataItem labelCode={"l.address.fax"} data={address?.fax} />
          <LabeledDataItem labelCode={"l.address.address"} data={address?.address} />
          <LabeledDataItem labelCode={"l.url"} data={baseUniversity?.webSite} />
          <LabeledDataItem labelCode={"l.urlDiplomaRegister"} data={baseUniversity?.urlDiplomaRegister} />
          <LabeledDataItem labelCode={"l.applicationUniversityName"} data={baseUniNameTranslated} />
        </GridContainer>
      </TextSection>
      {trainingCourseUniversities && trainingCourseUniversities.length > 1 && (
        <TextSection label={"l.secondary.universities"} withDivider>
          {trainingCourseUniversities
            .filter((x) => x.ordNum === 2)
            .map((uni, index) => (
              <Fragment key={"sec-uni-" + index}>
                <GridContainer>
                  <LabeledDataItem labelCode={"l.name"} data={uni?.university?.bgName} />
                  <LabeledDataItem labelCode={"l.originalName"} data={uni?.university?.orgName} />
                  <LabeledDataItem labelCode={"l.address.country"} data={uni?.university?.country?.name} />
                  <LabeledDataItem labelCode={"l.address.city"} data={uni?.university?.address?.city} />
                  <LabeledDataItem labelCode={"l.address.phone"} data={uni?.university?.address?.phone} />
                  <LabeledDataItem labelCode={"l.address.fax"} data={uni?.university?.address?.fax} />
                  <LabeledDataItem labelCode={"l.address.address"} data={uni?.university?.address?.address} />
                  <LabeledDataItem labelCode={"l.url"} data={uni?.university?.webSite} />
                  <LabeledDataItem labelCode={"l.urlDiplomaRegister"} data={uni?.university?.urlDiplomaRegister} />
                  <LabeledDataItem labelCode={"l.applicationUniversityName"} data={uni?.universityNameTranslated} />
                </GridContainer>
                {index < trainingCourseUniversities.length - 2 && <DividerSpg />}
              </Fragment>
            ))}
        </TextSection>
      )}
    </AccordionDetails>
  );
};
export default ViewBaseUniversitySection;
