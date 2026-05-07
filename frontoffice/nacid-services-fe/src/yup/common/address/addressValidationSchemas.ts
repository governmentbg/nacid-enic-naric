import * as yup from "yup";
import { POST_CODE_VALIDATION_REGEX, NAME_CYR_OR_LAT_VALIDATION_REGEX } from "../../utils/regexExpressions";
import { initializeYup } from "../../utils/yupUtils";

export const createContactAddressValidationSchema = () => {
  initializeYup(yup);
  const contactAddressValidationSchema = yup
    .object({
      fax: yup.string().optional(),
      email: yup.string().required().email(),
      postBox: yup.string().optional(),
    })
    .concat(createBaseAddressFullValidationSchema());

  return contactAddressValidationSchema;
};

export const createReceiverAddressValidationSchema = () => {
  initializeYup(yup);

  const receiverAddressValidationSchema = yup
    .object({
      name: yup.string().required().matches(NAME_CYR_OR_LAT_VALIDATION_REGEX),
    })
    .concat(createBaseAddressFullValidationSchema());

  return receiverAddressValidationSchema;
};

const createBaseAddressFullValidationSchema = () => {
  initializeYup(yup);

  const baseAddressValidationSchema = yup.object({
    city: yup.mixed().when("country.id", {
      is: (id) => id !== "BG",
      then: yup.string().required(),
    }),
    postCode: yup.string().required().matches(POST_CODE_VALIDATION_REGEX),
    address: yup.string().required(),
    phone: yup.string().required(),
    country: yup.object({
      id: yup.string().required(),
    }),
    settlement: yup.mixed().when("country.id", {
      is: (id) => id === "BG",
      then: yup.object({
        id: yup.string().required(),
      }),
    }),
  });

  return baseAddressValidationSchema;
};
