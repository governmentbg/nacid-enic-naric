import { ApplicantType } from "../types/common/personTypes";
import { initialCompany, initialServicesNaturalPerson, initialUniversity } from "../init/common/personInitialValues";
import { CertificateReceiveForm, fillNonNullValues, isEmpty, THUNK_STATUS } from "@duosoftbg/nacid-components";
import { initialContactAddress, initialReceiverAddress } from "../init/common/addressInitialValues";
import { initialResultReceive } from "../init/common/applicantDetailsInitialValues";

export const fillUserDetailsInForm = (
  reset,
  initialFormValues,
  formStepCompleted,
  loggedUser,
  hasCompanyApplicant = true,
  hasUniversityApplicant = false,
  receiveResult
) => {
  if (formStepCompleted === false && loggedUser.status === THUNK_STATUS.FULFILLED) {
    let form = { ...initialFormValues };

    if (!userContactAddressIsEmpty(loggedUser)) {
      form.hasContactAddress = true;
      let contactAddress = { ...initialContactAddress };
      if (loggedUser.data.userDetails.contactAddress && loggedUser.data.userDetails.contactAddress != null) {
        fillNonNullValues(loggedUser.data.userDetails.contactAddress, contactAddress);
      }
      form.contactAddress = contactAddress;
    }

    let resultReceive = { resultReceive: { ...initialResultReceive }, receiverAddress: { ...initialReceiverAddress } };
    if (loggedUser.data.userDetails.receiverAddress && loggedUser.data.userDetails.receiverAddress != null) {
      fillNonNullValues(loggedUser.data.userDetails.receiverAddress, resultReceive.receiverAddress);
    }
    if (loggedUser.data.userDetails.resultReceive && loggedUser.data.userDetails.resultReceive != null) {
      const foundValue = receiveResult.filter((rec) => rec.id === loggedUser.data.userDetails.resultReceive.id);
      if (foundValue.length > 0) {
        fillNonNullValues(foundValue[0], resultReceive.resultReceive);
      } else {
        fillNonNullValues(loggedUser.data.userDetails.resultReceive, resultReceive.resultReceive);
      }
    }
    if (form.certificateReceiveForms == null) {
      form.resultReceive = resultReceive;
    } else if (resultReceive.resultReceive.certificateReceiveFormCode === CertificateReceiveForm.ELECTRONIC) {
      form.resultReceiveElectronic = resultReceive;
      form.certificateReceiveForms = [CertificateReceiveForm.ELECTRONIC];
    } else if (resultReceive.resultReceive.certificateReceiveFormCode === CertificateReceiveForm.PAPER) {
      form.resultReceivePaper = resultReceive;
      form.certificateReceiveForms = [CertificateReceiveForm.PAPER];
    }

    let person = { ...initialServicesNaturalPerson };
    const userPerson = loggedUser.data.userDetails;
    fillNonNullValues(userPerson, person);
    person.userName = loggedUser.data.userDetails.username;

    if (
      hasCompanyApplicant &&
      loggedUser.data.userDetails.isRepresentative &&
      loggedUser.data.userDetails.representativeType === "COMPANY_REPRESENTATIVE" &&
      loggedUser.data.userDetails.representedCompany
    ) {
      const applicant = {
        ...initialFormValues.applicant,
        applicantType: ApplicantType.COMPANY,
        company: { ...initialCompany, companyIdentifier: loggedUser.data.userDetails.representedCompany },
      };
      form.applicant = applicant;
      form.representative = person;
      form.applicantHasRepresentative = true;
    } else if (
      hasUniversityApplicant &&
      loggedUser.data.userDetails.isRepresentative &&
      loggedUser.data.userDetails.representativeType === "UNIVERSITY_REPRESENTATIVE" &&
      loggedUser.data.userDetails.representedUniversity
    ) {
      const applicant = {
        ...initialFormValues.applicant,
        applicantType: ApplicantType.UNIVERSITY,
        university: { ...initialUniversity, universityIdentifier: loggedUser.data.userDetails.representedUniversity },
      };
      form.applicant = applicant;
      form.representative = person;
      form.applicantHasRepresentative = true;
    } else {
      form.applicant = {
        naturalPerson: person,
        company: { ...initialCompany },
        university: { ...initialUniversity },
        applicantType: ApplicantType.NATURAL_PERSON,
      };
    }

    if (loggedUser.data.userDetails.isRepresentative && loggedUser.data.userDetails.representativeCapacity) {
      form.representativeCapacity = loggedUser.data.userDetails.representativeCapacity;
    }

    reset(form);
  }
};

const userContactAddressIsEmpty = (loggedUser) => {
  if (loggedUser.data.userDetails.contactAddress) {
    if (!isEmpty(loggedUser.data.userDetails.contactAddress.address)) {
      return false;
    }
    if (!isEmpty(loggedUser.data.userDetails.contactAddress.city)) {
      return false;
    }
    if (
      loggedUser.data.userDetails.contactAddress.country &&
      !isEmpty(loggedUser.data.userDetails.contactAddress.country.id)
    ) {
      return false;
    }
    if (
      loggedUser.data.userDetails.contactAddress.settlement &&
      !isEmpty(loggedUser.data.userDetails.contactAddress.settlement.id)
    ) {
      return false;
    }
    if (!isEmpty(loggedUser.data.userDetails.contactAddress.phone)) {
      return false;
    }
    if (!isEmpty(loggedUser.data.userDetails.contactAddress.postCode)) {
      return false;
    }
    if (!isEmpty(loggedUser.data.userDetails.contactAddress.email)) {
      return false;
    }
    if (!isEmpty(loggedUser.data.userDetails.contactAddress.fax)) {
      return false;
    }
    if (!isEmpty(loggedUser.data.userDetails.contactAddress.postBox)) {
      return false;
    }
  }

  return true;
};
