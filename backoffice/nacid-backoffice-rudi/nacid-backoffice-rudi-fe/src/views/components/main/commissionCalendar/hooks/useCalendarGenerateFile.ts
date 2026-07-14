import { AsyncCallArgs, useAsyncCall } from "@duosoftbg/nacid-components";

import { downloadCommissionReport } from "../../../../../axios/api/services";

const useCalendarGenerateFile = ({ calendarId }) => {
  const { asyncCall } = useAsyncCall();
  const generateFile = (template, reportType, fileName) => {
    const asyncCreation: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: downloadCommissionReport(template, reportType, calendarId),
      onSuccess: (response) => {
        var downloadLink = window.document.createElement("a");
        downloadLink.href = window.URL.createObjectURL(
          new Blob([response.data], { type: response.headers.contentType }),
        );
        downloadLink.download = fileName;
        document.body.appendChild(downloadLink);
        downloadLink.click();
        document.body.removeChild(downloadLink);
      },
    };
    asyncCall(asyncCreation);
  };

  return { generateFile };
};

export default useCalendarGenerateFile;
