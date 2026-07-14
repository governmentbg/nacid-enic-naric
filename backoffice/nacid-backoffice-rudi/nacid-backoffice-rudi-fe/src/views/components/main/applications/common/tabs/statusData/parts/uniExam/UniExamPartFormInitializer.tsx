import {
  AlertSpg,
  AsyncCallArgs,
  BoxSpg,
  CircularLoader,
  isArrayNotEmpty,
  isNotEmpty,
  useAsyncCall,
} from "@duosoftbg/nacid-components";
import { useParams } from "react-router-dom";
import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import UniExamPartFormProvider from "./UniExamPartFormProvider";
import { getApplicationUniExaminationData } from "../../../../../../../../../axios/api/services";
import { IconButton, Tooltip, Typography } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEye } from "@fortawesome/free-solid-svg-icons";
import { ProcessEnvironments } from "@duosoftbg/nacid-backoffice-components";

const UniExamPartFormInitializer = ({ children }) => {
  const { id: applicationId } = useParams();
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();

  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getApplicationUniExaminationData(applicationId),
      onSuccess: (response) => {
        setData(response);
        setLoading(false);
        setError(false);
      },
      onError: () => {
        setError(true);
        setLoading(false);
      },
    };
    asyncCall(asyncCallArgs);

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [applicationId]);

  if (loading) {
    return <CircularLoader />;
  }

  if (error) {
    return <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>;
  }

  if (isNotEmpty(data) && isArrayNotEmpty(data.examinations)) {
    return (
      <>
        {data.examinations.map((examination, index) => {
          return (
            <BoxSpg
              className={index === 0 && `first-university-${examination.university.id}`}
              id={`exam-form-${examination.university.id}`}
              key={examination.university.id}
              mt={7}
            >
              <UniExamPartFormProvider applicationId={applicationId} initialData={examination}>
                <Typography variant={"h4"} color={"primary"}>
                  {examination.university.bgName}
                  <Tooltip title={t("l.university.view")}>
                    <a
                      href={`${ProcessEnvironments.Module.BackOffice.Core}/nomenclatures/universities/view?id=${examination?.university?.id}`}
                      style={{ textDecoration: "none" }}
                      target="_blank"
                      rel="noreferrer"
                    >
                      <IconButton>
                        <FontAwesomeIcon style={{ fontSize: 18, cursor: "pointer" }} icon={faEye} color={"primary"} />
                      </IconButton>
                    </a>
                  </Tooltip>
                </Typography>
                {children}
              </UniExamPartFormProvider>
            </BoxSpg>
          );
        })}
      </>
    );
  }

  return null;
};

export default UniExamPartFormInitializer;
