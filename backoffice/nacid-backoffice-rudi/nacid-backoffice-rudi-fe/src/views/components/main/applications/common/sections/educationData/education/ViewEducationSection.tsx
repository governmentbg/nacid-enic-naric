import React from "react";
import { GridContainer, GridItem, LabeledDataItem, TextSection } from "@duosoftbg/nacid-components";
import { AccordionDetails, Typography } from "@mui/material";
import useAppSelector from "../../../../../../../../hooks/redux/base/useAppSelector";
import { AppType } from "@duosoftbg/nacid-backoffice-components";

type ViewEducationSectionProps = {
  appType: AppType;
};

const getTrainingFormTxt = (trainingCourse) => {
  const notes = trainingCourse?.trainingForm?.notes;
  const trainingForm = trainingCourse?.trainingForm?.trainingForm?.name;

  if (notes) {
    if (trainingForm) {
      return `${trainingForm} (${notes})`;
    } else {
      return notes;
    }
  }

  return trainingForm || "";
};

const ViewEducationSection = ({ appType }: ViewEducationSectionProps) => {
  const viewData = useAppSelector((state) => {
    return state["ViewData"];
  });

  const trainingCourse = viewData.data?.trainingCourse;
  const trainingLocations = trainingCourse?.trainingLocations;
  const trainingCourseSpecialities = trainingCourse?.trainingCourseSpecialities;

  const graduationWays = trainingCourse?.graduationWays;
  return (
    <>
      {trainingLocations && trainingLocations.length > 0 && (
        <AccordionDetails>
          <TextSection label={"l.trainingLocations"} withDivider>
            <GridContainer>
              {trainingLocations?.map((row) => (
                <GridItem sm={6} md={6} key={row.id}>
                  <Typography>{row.city ? row.country.name + " (" + row.city + ")" : row.country.name}</Typography>
                </GridItem>
              ))}
            </GridContainer>
          </TextSection>
        </AccordionDetails>
      )}
      {(trainingCourse?.originalEduLevelName || trainingCourse?.originalEduLevelTranslated) && (
        <AccordionDetails>
          <TextSection label={"l.original.edu.level"} withDivider>
            <GridContainer>
              {appType !== AppType.UDIREC_APPLICATION && (
                <LabeledDataItem labelCode={"l.recognitionCategory"} data={trainingCourse?.recognitionCategory?.name} />
              )}
              <LabeledDataItem
                labelCode={"l.originalEduLevelTranslated"}
                data={trainingCourse?.originalEduLevelTranslated}
              />
              <LabeledDataItem labelCode={"l.originalEduLevelName"} data={trainingCourse?.originalEduLevelName} />
            </GridContainer>
          </TextSection>
        </AccordionDetails>
      )}
      {appType !== AppType.DOCREC_APPLICATION &&
        trainingCourseSpecialities &&
        trainingCourseSpecialities.length > 0 && (
          <AccordionDetails>
            <TextSection label={"l.specialities"} withDivider>
              <GridContainer>
                {trainingCourseSpecialities.map((row) => (
                  <GridItem sm={12} md={12} key={row.id}>
                    <Typography>
                      {row.originalSpeciality ? row.speciality + " (" + row.originalSpeciality + ")" : row.speciality}
                    </Typography>
                  </GridItem>
                ))}
              </GridContainer>
            </TextSection>
          </AccordionDetails>
        )}
      {(trainingCourse?.qualification || trainingCourse?.originalQualification || trainingCourse?.profGroup) && (
        <AccordionDetails>
          <TextSection label={"l.professional.qualification"} withDivider>
            <GridContainer>
              <LabeledDataItem
                labelCode={"l.professional.qualification.translation"}
                data={trainingCourse?.qualification}
              />
              <LabeledDataItem
                labelCode={"l.professional.qualification.name"}
                data={trainingCourse?.originalQualification}
              />
              <LabeledDataItem labelCode={"l.profGroup"} data={trainingCourse?.profGroup?.name} />
              <LabeledDataItem
                labelCode={"l.profGroup.educationArea"}
                data={trainingCourse?.profGroup?.educationArea?.name}
              />
            </GridContainer>
          </TextSection>
        </AccordionDetails>
      )}
      {(trainingCourse?.bolognaCycle ||
        trainingCourse?.accessedBolognaCycle ||
        trainingCourse?.nationalQualificationFramework ||
        trainingCourse?.accessedNationalQualificationFramework ||
        trainingCourse?.europeanQualificationFramework ||
        trainingCourse?.accessedEuropeanQualificationFramework) && (
        <AccordionDetails>
          <TextSection label={"t.additional.data"} withDivider>
            <GridContainer>
              <LabeledDataItem labelCode={"l.bolognaCycle"} data={trainingCourse?.bolognaCycle?.name} />
              <LabeledDataItem labelCode={"l.accessedBolognaCycle"} data={trainingCourse?.accessedBolognaCycle?.name} />
              <LabeledDataItem
                labelCode={"l.nationalQualificationFramework"}
                data={trainingCourse?.nationalQualificationFramework?.name}
              />
              <LabeledDataItem
                labelCode={"l.accessedNationalQualificationFramework"}
                data={trainingCourse?.accessedNationalQualificationFramework?.name}
              />
              <LabeledDataItem
                labelCode={"l.europeanQualificationFramework"}
                data={trainingCourse?.europeanQualificationFramework?.name}
              />
              <LabeledDataItem
                labelCode={"l.accessedEuropeanQualificationFramework"}
                data={trainingCourse?.accessedEuropeanQualificationFramework?.name}
              />
            </GridContainer>
          </TextSection>
        </AccordionDetails>
      )}
      {(trainingCourse?.trainingStart ||
        trainingCourse?.trainingEnd ||
        trainingCourse?.trainingDuration ||
        trainingCourse?.durationUnit?.name ||
        trainingCourse?.credits ||
        trainingCourse?.ectsCredits ||
        trainingCourse?.creditHours ||
        trainingCourse?.trainingForm?.trainingForm?.name ||
        trainingCourse?.graduationDocumentType?.name) && (
        <AccordionDetails>
          <TextSection label={"l.education.data"} withDivider>
            <GridContainer>
              <LabeledDataItem
                labelCode={"l.trainingStart"}
                data={trainingCourse?.trainingStart ? new Date(trainingCourse?.trainingStart).getFullYear() : ""}
              />
              <LabeledDataItem
                labelCode={"l.trainingEnd"}
                data={trainingCourse?.trainingEnd ? new Date(trainingCourse?.trainingEnd).getFullYear() : ""}
              />
              <LabeledDataItem labelCode={"l.trainingDuration"} data={trainingCourse?.trainingDuration} />
              <LabeledDataItem labelCode={"l.durationUnit"} data={trainingCourse?.durationUnit?.name} />
              <LabeledDataItem labelCode={"l.credits"} data={trainingCourse?.credits} />
              <LabeledDataItem labelCode={"l.ectsCredits"} data={trainingCourse?.ectsCredits} />
              <LabeledDataItem labelCode={"l.creditHours"} data={trainingCourse?.creditHours} />
              <LabeledDataItem labelCode={"l.trainingForm"} data={getTrainingFormTxt(trainingCourse)} />
              {appType !== AppType.DOCREC_APPLICATION && (
                <LabeledDataItem
                  labelCode={"l.graduationDocumentTypeId"}
                  data={trainingCourse?.graduationDocumentType?.name}
                />
              )}
            </GridContainer>
          </TextSection>
        </AccordionDetails>
      )}
      {graduationWays && graduationWays.length > 0 && (
        <AccordionDetails>
          <TextSection label={"l.graduationWay"} withDivider>
            <GridContainer>
              {graduationWays?.map((row) => (
                <GridItem sm={6} md={6} key={row.id}>
                  <Typography>
                    {row.notes ? row.graduationWay.name + " (" + row.notes + ")" : row.graduationWay.name}
                  </Typography>
                </GridItem>
              ))}
            </GridContainer>
          </TextSection>
        </AccordionDetails>
      )}
    </>
  );
};
export default ViewEducationSection;
