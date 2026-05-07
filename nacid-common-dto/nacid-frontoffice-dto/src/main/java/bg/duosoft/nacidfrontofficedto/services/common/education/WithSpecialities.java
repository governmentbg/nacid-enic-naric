package bg.duosoft.nacidfrontofficedto.services.common.education;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 30.08.2022
 * Time: 13:51
 */
public interface WithSpecialities {

    List<SpecialityDTO> getSpecialities();

    void setSpecialities(List<SpecialityDTO> specialities);

    SpecialityDTO getSpecialitySingle();

    void setSpecialitySingle(SpecialityDTO specialitySingle);
}
