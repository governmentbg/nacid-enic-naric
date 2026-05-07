import useAppDispatch from "../../hooks/redux/base/useAppDispatch";
import { useEffect } from "react";
import { selectService } from "../../store/redux/slice/SelectedService/selectedService";

const ServiceConfigWrapperPage = ({ wrappedPage, serviceConfig }) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(selectService(serviceConfig));
  }, [dispatch, serviceConfig]);

  return <>{wrappedPage}</>;
};
export default ServiceConfigWrapperPage;
